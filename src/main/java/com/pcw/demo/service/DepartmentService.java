package com.pcw.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcw.demo.model.Department;

import com.pcw.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository deprep;
	
    public Department addDepartment(Department department) {	
		return deprep.save(department);
	}
	
	public List<Department>getDepartments(){
		return deprep.findAll();
	}
	
	public Department getDepartmentById(int departmemntid) {
		return deprep.findByDepartmentid(departmemntid);
	}

}
