package com.example.alim.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.alim.dto.ResponseDto;
import com.example.alim.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {


	@Autowired
	MemberService memberService;
	
	
	@GetMapping("/loginPage")
	public String loginPage()
	{
		log.info("로그인 페이지 진입");
		return "login";
	}
	
	@GetMapping("/signUpPage")
	public String signUpPage()
	{
		log.info("회원가입 페이지 진입");
		return "signUp";
	}
			
	@PostMapping("/signUp")
	public ResponseEntity<ResponseDto> signUp(@RequestBody Map<String,Object> memberData)
	{
		log.info("회원가입 처리");
		return memberService.signUp(memberData);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody Map<String,Object> memberData, HttpSession session)
	{
		log.info("로그인 처리");
		return memberService.login(memberData, session);
	}
	
	
	@GetMapping("memberList")
	public ResponseEntity<ResponseDto> getMemberList()
	{
		return memberService.getMemberList();
	}
	
	@GetMapping("/logout")
    public ResponseEntity<ResponseDto> logout(HttpSession session) {

        return memberService.logout(session);
    }

}
