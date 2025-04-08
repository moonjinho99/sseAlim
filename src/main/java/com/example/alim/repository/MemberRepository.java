package com.example.alim.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.alim.entity.Member;
import com.example.alim.type.MemberRole;

public interface MemberRepository extends JpaRepository<Member,Integer> {

	@Query("select m from Member m where m.memberId = :memberId and m.memberPw  = :memberPw")
	Optional<Member> loginMember(@Param("memberId") String memberId, @Param("memberPw") String memberPw);
	
	List<Member> findByMemberRole(MemberRole memberRole);
}
