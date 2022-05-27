package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Results;

public interface ResultsRepo extends JpaRepository<Results, Long>{

	Results findByResultid(Long resultid);
}
