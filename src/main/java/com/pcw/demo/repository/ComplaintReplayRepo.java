package com.pcw.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Complaint;
import com.pcw.demo.model.ComplaintReplay;

public interface ComplaintReplayRepo extends JpaRepository<ComplaintReplay,Long>{
	public ComplaintReplay findByComplaint(Optional<Complaint> complaint);

}
