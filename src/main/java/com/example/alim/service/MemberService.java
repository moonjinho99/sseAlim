package com.example.alim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.alim.dto.MemberDto;
import com.example.alim.dto.ResponseDto;
import com.example.alim.entity.Member;
import com.example.alim.repository.MemberRepository;
import com.example.alim.type.MemberRole;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {
	
	@Autowired
	MemberRepository memberRepository;
	
	//private final PasswordEncoder passwordEncoder;

	public ResponseEntity<ResponseDto> signUp(Map<String,Object> memberData)
	{
		
		try {			
			String member_name = memberData.get("member_name").toString();
			String member_id = memberData.get("member_id").toString();
			String member_pw = memberData.get("member_pw").toString();	
			MemberRole member_role = MemberRole.valueOf(memberData.get("member_role").toString());
			//String encodedPw = passwordEncoder.encode(member_pw);
			
			MemberDto memberDto = MemberDto.builder()
								.memberId(member_id)
								.memberName(member_name)
								.memberPw(member_pw)
								.memberRole(member_role)
								.build();
			
			memberRepository.save(Member.toEntity(memberDto));			
			ResponseDto responseDto = ResponseDto.builder().message("success").body(memberDto).build();
			
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			
		}catch(Exception e)
		{
			log.error("ERROR : "+e.getMessage());
			ResponseDto responseDto = ResponseDto.builder().message("fail").build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
				
	}
	
	public ResponseEntity<ResponseDto> login(Map<String,Object> memberData, HttpSession session)
	{
		ResponseDto responseDto = null;
		
		try {
			String memberId = memberData.get("member_id").toString();
			String memberPw = memberData.get("member_pw").toString();
			
			Optional<Member> member = memberRepository.loginMember(memberId, memberPw);
			
			if(member.isPresent())
			{
				session.setAttribute("loginMember", member.get());
				responseDto = ResponseDto.builder().message("success").body(MemberDto.toDto(member.get())).build();
			}else {				
				responseDto = ResponseDto.builder().message("fail").build();
			}
							
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		}catch(Exception e)
		{
			log.error("ERROR : "+e.getMessage());
			responseDto = ResponseDto.builder().message("error").build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		
	}
	
	public ResponseEntity<ResponseDto> getMemberList()
	{
		ResponseDto responseDto = null;
		
		try {
			List<Member> memberList = memberRepository.findByMemberRole(MemberRole.MEMBER);
									
			List<MemberDto> memberDtoList = new ArrayList<MemberDto>();
			
			for(Member member : memberList)
			{
				MemberDto dto = MemberDto.toDto(member);
				dto.setMemberPw(null);
				dto.setMemberRole(null);
				memberDtoList.add(dto);
			}
									
			responseDto = ResponseDto.builder().message("success").body(memberDtoList).build();
			
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);		
		}catch(Exception e)
		{
			responseDto = ResponseDto.builder().message("error").build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		
	}
	
	public ResponseEntity<ResponseDto> logout(HttpSession session)
	{   		
		try {
			 session.invalidate();		 
			 return ResponseEntity.status(HttpStatus.OK).body(null);		
		}catch(Exception e)
		{
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);		
		}

	}
	
}
