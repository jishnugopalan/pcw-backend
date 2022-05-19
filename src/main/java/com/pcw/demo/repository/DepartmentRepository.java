package com.pcw.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pcw.demo.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer>{
	Department findByDepartment(String department);
	Department findByDepartmentid(int departmentid);
	int deleteByDepartmentid(int departmentid);
	//@Query(value="select * from departments where department like '%1?%' ",nativeQuery = true)
	List findByDepartmentContaining(String department);

}
