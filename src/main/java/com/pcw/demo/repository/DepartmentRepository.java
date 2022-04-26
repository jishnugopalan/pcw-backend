package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer>{
	Department findByDepartment(String department);
	Department findByDepartmentid(int departmentid);
	

}
