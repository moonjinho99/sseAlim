package com.example.alim.entity;



import java.util.Date;
import com.example.alim.dto.AlimDto;
import com.example.alim.type.AlimType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="alim")
public class Alim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="alim_num")
	Integer alimNum;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	AlimType type;
	
	@Column(name="content")
	String content;
	
	@Column(name="send_time")
	Date sendTime;
	
	@Column(name="read_chk")
	boolean readChk;
	
	@Column(name="receiver_num")
	Integer receiverNum;
		
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_num", referencedColumnName = "member_num", insertable = false, updatable = false)
    private Member member;
	
	
	public static Alim toEntity(AlimDto dto)
	{
		return Alim.builder()
				.alimNum(dto.getAlimNum())
				.type(dto.getType())
				.content(dto.getContent())
				.sendTime(dto.getSendTime())
				.readChk(dto.isReadChk())
				.receiverNum(dto.getReceiverNum())
				.build();
	}
	
}
