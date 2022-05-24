package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Summary;

public interface SummaryRepo extends JpaRepository<Summary, Long>{

}
