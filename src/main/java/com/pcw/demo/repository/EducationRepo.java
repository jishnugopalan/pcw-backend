package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Education;

public interface EducationRepo extends JpaRepository<Education, Integer>{

}
