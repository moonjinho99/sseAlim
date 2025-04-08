package com.example.alim.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.alim.entity.Alim;

import jakarta.transaction.Transactional;



public interface AlimRepository extends JpaRepository<Alim,Integer> {

	List<Alim>findByReceiverNum(Integer receiverNum);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE alim SET read_chk = 1 WHERE alim_num = :alimNum", nativeQuery = true)
	void updateAlimReadChk(@Param("alimNum") Integer alimNum);

}
