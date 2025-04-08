package com.example.alim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.alim.dto.MemberDto;
import com.example.alim.entity.Member;
import com.example.alim.type.MemberRole;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping("/")
	public String rootRedirect(HttpSession session)
	{
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember != null) {
			
			if(loginMember.getMemberRole().equals(MemberRole.ADMIN))
			{
				return "send";
			}
			
			return "receive";
		}
		else {
			return "redirect:/member/loginPage";
		}
	}
	

}
