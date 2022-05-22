package com.pcw.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Department;
import com.pcw.demo.model.EligiblePlacementDepartments;
import com.pcw.demo.model.User;

import org.springframework.data.jpa.repository.Query;
public interface EligiblePlacementDepartmentsRepo extends JpaRepository<EligiblePlacementDepartments, Integer>{
	public List findByDepartmentid(int pid);
	public List findByPid(Long pid);
	
	@Query(value = "select * from eligible_placement_departments where pid=?1 and departmentid=?2", nativeQuery = true)
	EligiblePlacementDepartments deleteEligibleDep(int pid,int departmentid); 
	
	@Query(value="select * from departments where departmentid not in(select departmentid from eligible_placement_departments where pid=?1 )",nativeQuery = true)
	List getDepartmentNotinElgibleDep(int pid);
	

}
