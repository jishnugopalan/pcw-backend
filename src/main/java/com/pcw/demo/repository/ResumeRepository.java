package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pcw.demo.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long>{

	@Query(value="select resumeid from resume where userid=?1",nativeQuery = true)
	public Long findByUserid(Long userid);
}
