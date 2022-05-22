package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Projects;

public interface ProjectsRepo extends JpaRepository<Projects, Long>{

}
