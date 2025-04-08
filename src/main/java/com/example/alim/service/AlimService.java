package com.example.alim.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.alim.dto.AlimDto;
import com.example.alim.dto.ResponseDto;
import com.example.alim.entity.Alim;
import com.example.alim.repository.AlimRepository;
import com.example.alim.type.AlimType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlimService {

	private final Map<Integer, SseEmitter> emitters = new ConcurrentHashMap<Integer, SseEmitter>();
	
	@Autowired
	AlimRepository alimRepository;
	
	public SseEmitter subscribe(Integer memberNum) {
		
		log.info(memberNum+"번 회원 연결");
		
		SseEmitter emitter = new SseEmitter(60*60*1000L);
		emitters.put(memberNum, emitter);
		
		emitter.onCompletion(()->emitters.remove(memberNum));
		emitter.onTimeout(() -> emitters.remove(memberNum));
		emitter.onError(e -> emitters.remove(memberNum));
		
		try {
			emitter.send(SseEmitter.event().name("connect").data("connected"));
		} catch(IOException e) {
			emitters.remove(memberNum);
		}
		
		return emitter;
	}
	
	public ResponseEntity<ResponseDto> sendToMember(Map<String,Object> alimData) {
		
		ResponseDto responseDto = null;	
		try {			
			List<Integer> receivers = ((List<?>) alimData.get("receivers")).stream()
			        .map(Object::toString)
			        .map(Integer::parseInt)
			        .collect(Collectors.toList());
			
			String message = alimData.get("message").toString();
			AlimType type = AlimType.valueOf(alimData.get("type").toString());
			
			for(Integer receiverNum : receivers)
			{
				SseEmitter emitter = emitters.get(receiverNum);				
				AlimDto dto = AlimDto.builder().type(type).content(message).sendTime(new Date()).readChk(false).receiverNum(receiverNum).build();
				
				if(emitter != null) {
					try {
						emitter.send(SseEmitter.event().name("alim").data(dto));
					}catch(IOException e) {
						emitters.remove(receiverNum);
					}							
				}
				alimRepository.save(Alim.toEntity(dto));		
			}
			responseDto = ResponseDto.builder().message("success").build();						
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
			
		}catch(Exception e)
		{
			responseDto = ResponseDto.builder().message("error").build();	
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
			
	}
		
	public ResponseEntity<ResponseDto> getAlimList(Integer memberNum) {		
		ResponseDto responseDto = null;	
		try {			
			List<Alim> alimList = alimRepository.findByReceiverNum(memberNum);
			List<AlimDto> alimDtoList = new ArrayList<>();
			
			for(Alim alim : alimList)
			{
				alimDtoList.add(AlimDto.toDto(alim));
			}
						
			responseDto = ResponseDto.builder().message("success").body(alimDtoList).build();
			
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		}catch(Exception e)
		{
			log.error(e.getMessage());
			responseDto = ResponseDto.builder().message("error").body(null).build();		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		
	}
	
	public ResponseEntity<ResponseDto> readAlim(Integer alimNum) {
		ResponseDto responseDto = null;	
		try {
			alimRepository.updateAlimReadChk(alimNum);		
			responseDto = ResponseDto.builder().message("success").build();
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);		
		}catch(Exception e)
		{		
			log.error("ERROR : "+e.getMessage());
			responseDto = ResponseDto.builder().message("fail").build();	
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);			
		}		
	}
	
	
}
