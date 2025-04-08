package com.example.alim.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.alim.dto.AlimDto;
import com.example.alim.dto.ResponseDto;
import com.example.alim.service.AlimService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/alim")
@Controller
public class AlimController {

	@Autowired
	AlimService alimService;
	
    @GetMapping(value = "/subscribe/{memberNum}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable Integer memberNum) {
        return alimService.subscribe(memberNum);
    }
    
    @PostMapping("/send")
    public ResponseEntity<ResponseDto> sendAlim(@RequestBody Map<String,Object> alimData){  	   	
    	log.info(alimData.toString());   	
    	return alimService.sendToMember(alimData);   	
    }

    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<ResponseDto> sendAlim(@RequestParam("memberNum") Integer memberNum){  	   	
    	return alimService.getAlimList(memberNum);   	
    }
	
    @PostMapping("/read")
    public ResponseEntity<ResponseDto> read(@RequestBody Map<String,Object> alimData){  	   	
    	return alimService.readAlim((int)alimData.get("alimNum"));   	
    }
    
}
