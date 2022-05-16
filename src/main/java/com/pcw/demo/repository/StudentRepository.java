package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.StudentDetails;

public interface StudentRepository extends JpaRepository<StudentDetails, Integer>{
	StudentDetails findByUserid(Long userid);
	int deleteByUserid(Long userid);

}
