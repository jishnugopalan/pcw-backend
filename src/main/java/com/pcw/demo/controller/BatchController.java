package com.pcw.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.Batch;
import com.pcw.demo.service.BatchService;



@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class BatchController {
	@Autowired
	private BatchService batchService;
	
	//Add a new batch
	@PostMapping("/addbatch")
	public Batch addBatch(@RequestBody Batch batch) throws Exception {
		return batchService.addBatch(batch);
	}
	
	//find batch by department by id
	@GetMapping("/viewbydepartmentid")
	public List findByDepartmentid(@RequestParam int departmentid) {
		System.out.println("in fun");
		return batchService.findByDepartmentid(departmentid);
	}
	
	//find batch by batch id
	@GetMapping("/viewbybatchid")
	public Batch findByBatchid(@RequestParam int batchid) {
		return batchService.findByBatchid(batchid);
	}
	//delete a batch
	@DeleteMapping("/delete-batch")
	public ResponseEntity<?> deleteBatchByid(@RequestParam int batchid) {
		return batchService.deleteBatchByid(batchid);
	}
	
	
	

}
