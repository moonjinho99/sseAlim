package com.example.alim.entity;

import com.example.alim.dto.MemberDto;
import com.example.alim.type.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="member_num")
	Integer memberNum;
	
	@Column(name="member_name")
	String memberName;
	
	@Column(name="member_id")
	String memberId;
	
	@Column(name="member_pw")
	String memberPw;
	
	@Enumerated(EnumType.STRING)
	@Column(name="member_role")
	private MemberRole memberRole;
	
	public static Member toEntity(MemberDto dto)
	{
		return Member.builder()
				.memberNum(dto.getMemberNum())
				.memberName(dto.getMemberName())
				.memberId(dto.getMemberId())
				.memberPw(dto.getMemberPw())
				.memberRole(dto.getMemberRole())
				.build();
	}
	
	
}
