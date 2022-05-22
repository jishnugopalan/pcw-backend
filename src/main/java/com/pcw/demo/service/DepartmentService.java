package com.pcw.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pcw.demo.model.Batch;
import com.pcw.demo.model.Department;
import com.pcw.demo.payload.response.MessageResponse;
import com.pcw.demo.repository.BatchRepository;
import com.pcw.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository deprep;
	@Autowired
	private BatchRepository batchRepo;
	
	//add a new department
    public Department addDepartment(Department department) throws Exception {	
    	
    	System.out.println(department);
    	List<Department> deps=deprep.findAll();
    	for(int i=0;i<deps.size();i++) {
    		Department d=deps.get(i);
    		if(d.getDepartment().equalsIgnoreCase(department.getDepartment())) {
    			throw new Exception(department.getDepartment()+" is already added");
    		}
    	}

       return deprep.save(department);
	}
	
    //get all department
	public List<Department>getDepartments(){
		return deprep.findAll();
	}
	
	//get department by id
	public Department getDepartmentById(int departmemntid) {
		return deprep.findByDepartmentid(departmemntid);
	}
	
	//delete a department
	public ResponseEntity<?> deleteDepartmentById(int departmentid) {
		List b=batchRepo.findByDepartmentid(departmentid);
		for(int i=0;i<b.size();i++) {
			Batch obj=(Batch) b.get(i);
			batchRepo.deleteById(obj.getBatchid());
		}
		deprep.deleteById(departmentid);
		return ResponseEntity.ok(new MessageResponse("Department deleted successfully"));
		
	}

	

}
