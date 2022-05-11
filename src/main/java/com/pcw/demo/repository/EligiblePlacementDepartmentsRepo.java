package com.pcw.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.EligiblePlacementDepartments;

public interface EligiblePlacementDepartmentsRepo extends JpaRepository<EligiblePlacementDepartments, Integer>{
	public List findByDepartmentid(int pid);

}
