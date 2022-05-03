package com.pcw.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@PostMapping("/addbatch")
	public Batch addBatch(@RequestBody Batch batch) {
		return batchService.addBatch(batch);
	}
	
	@GetMapping("/viewbydepartmentid")
	public List findByDepartmentid(@RequestParam int departmentid) {
		System.out.println("in fun");
		return batchService.findByDepartmentid(departmentid);
	}
	
	@GetMapping("/viewbybatchid")
	public Batch findByBatchid(@RequestParam int batchid) {
		return batchService.findByBatchid(batchid);
	}
	
	
	

}
