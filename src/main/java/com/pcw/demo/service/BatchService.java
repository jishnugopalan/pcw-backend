package com.pcw.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcw.demo.model.Batch;
import com.pcw.demo.model.Department;
import com.pcw.demo.repository.BatchRepository;



@Service
public class BatchService {
	@Autowired
	private BatchRepository batchRepo;
	
	public Batch addBatch(Batch batch) {
		return batchRepo.save(batch);
	}
	public List findByDepartmentid(int departmentid){
		return batchRepo.findByDepartmentid(departmentid);
	}
	public Batch findByBatchid(int batchid) {
		return batchRepo.findByBatchid(batchid);
	}

}
