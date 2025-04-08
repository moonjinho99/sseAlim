package com.example.alim.dto;

import com.example.alim.entity.Member;
import com.example.alim.type.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

	Integer memberNum;	
	String memberName;	
	String memberId;	
	String memberPw;
	MemberRole memberRole;
		
	public static MemberDto toDto(Member entity)
	{
		return MemberDto.builder()
				.memberNum(entity.getMemberNum())
				.memberName(entity.getMemberName())
				.memberId(entity.getMemberId())
				.memberPw(entity.getMemberPw())
				.memberRole(entity.getMemberRole())
				.build();
	}
}
