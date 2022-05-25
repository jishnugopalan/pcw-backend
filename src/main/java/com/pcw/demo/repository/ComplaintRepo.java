package com.pcw.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Complaint;
import com.pcw.demo.model.User;

public interface ComplaintRepo extends JpaRepository<Complaint, Long>{
	public List<Complaint> findByUser(User user);
	public Complaint findByComplaintid(Long complaintid);

}
