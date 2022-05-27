package com.pcw.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Complaint;
import com.pcw.demo.model.ComplaintReply;

public interface ComplaintReplayRepo extends JpaRepository<ComplaintReply,Long>{
	public ComplaintReply findByComplaint(Optional<Complaint> complaint);

}
