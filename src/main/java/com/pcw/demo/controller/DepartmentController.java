package com.pcw.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@GetMapping("/getdepartments")
	public List<Department> getRoles() {
		return depservice.getDepartments();
	}
	
	@PostMapping("/adddepartment")
	public Department addDepartment(@RequestBody Department department) {
		return depservice.addDepartment(department);
	}
	
	@GetMapping("/getdepartmentbyid")
	public Department getDepartmentById(@RequestParam int departmentid) {
		return depservice.getDepartmentById(departmentid);
	}

}
