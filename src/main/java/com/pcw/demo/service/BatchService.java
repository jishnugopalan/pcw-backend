package com.pcw.demo.service;


import java.util.List;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcw.demo.model.Batch;
import com.pcw.demo.model.Department;
import com.pcw.demo.payload.response.MessageResponse;
import com.pcw.demo.repository.BatchRepository;



@Service
public class BatchService {
	@Autowired
	private BatchRepository batchRepo;
	
	//Add a new batch
	public Batch addBatch(Batch batch) throws Exception {
		List<Batch> b=batchRepo.findAll();
		for(int i=0;i<b.size();i++) {
			Batch obj=b.get(i);
			if(obj.getBatchname().equalsIgnoreCase(batch.getBatchname())) {
				throw new Exception(batch.getBatchname()+" is already added");
			}
		}
		return batchRepo.save(batch);
	}
	//find batch by department id
	public List findByDepartmentid(int departmentid){
		return batchRepo.findByDepartmentid(departmentid);
	}
	//find batch by batchid
	public Batch findByBatchid(int batchid) {
		return batchRepo.findByBatchid(batchid);
	}
	
	//delete a batch
	public ResponseEntity<?> deleteBatchByid(int batchid) {
		batchRepo.deleteById(batchid);
		return ResponseEntity.ok(new MessageResponse("Batch deleted successfully"));
	}

}
