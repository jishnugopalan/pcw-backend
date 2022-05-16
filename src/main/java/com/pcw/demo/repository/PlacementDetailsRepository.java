package com.pcw.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.pcw.demo.model.PlacementDetails;

public interface PlacementDetailsRepository extends JpaRepository<PlacementDetails, Long>{
	public PlacementDetails findByPid(Long pid);
	public Long deleteByPid(Long pid);
	


}
