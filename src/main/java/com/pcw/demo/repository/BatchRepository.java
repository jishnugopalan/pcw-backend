package com.pcw.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Batch;




public interface BatchRepository extends JpaRepository<Batch, Integer>{
	List findByDepartmentid(int departmentid);
	Batch findByBatchid(int batchid);
	int deleteByBatchid(int batchid);

}
