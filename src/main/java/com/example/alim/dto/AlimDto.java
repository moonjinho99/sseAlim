package com.example.alim.dto;


import java.util.Date;

import com.example.alim.entity.Alim;
import com.example.alim.type.AlimType;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class AlimDto {
	
	Integer alimNum;	
	AlimType type;	
	String content;	
	Date sendTime;	
	boolean readChk;	
	Integer receiverNum;
	
	public static AlimDto toDto(Alim entity)
	{
		return AlimDto.builder()
				.alimNum(entity.getAlimNum())
				.type(entity.getType())
				.content(entity.getContent())
				.sendTime(entity.getSendTime())
				.readChk(entity.isReadChk())
				.receiverNum(entity.getReceiverNum())
				.build();
	}
	
}
