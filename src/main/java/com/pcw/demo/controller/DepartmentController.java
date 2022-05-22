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

import com.pcw.demo.model.Department;

import com.pcw.demo.service.DepartmentService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController

public class DepartmentController {
	@Autowired
	private DepartmentService depservice;
	
	
	//Get all departments
	@GetMapping("/getdepartments")
	public List<Department> getDepartments() {
		return depservice.getDepartments();
	}
	
	//Add a new department
	@PostMapping("/adddepartment")
	public Department addDepartment(@RequestBody Department department) throws Exception {
		return depservice.addDepartment(department);
	}
	
	//Get all department details by department id
	@GetMapping("/getdepartmentbyid")
	public Department getDepartmentById(@RequestParam int departmentid) {
		return depservice.getDepartmentById(departmentid);
	}
	//Delete a department
	@DeleteMapping("/delete-department")
	public ResponseEntity<?> deleteDepartmentById(@RequestParam int departmentid) {
		
		return depservice.deleteDepartmentById(departmentid);
		
	}

}
