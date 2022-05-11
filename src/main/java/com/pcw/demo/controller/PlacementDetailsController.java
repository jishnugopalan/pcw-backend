package com.pcw.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.EligiblePlacementDepartments;
import com.pcw.demo.model.PlacementDetails;
import com.pcw.demo.repository.EligiblePlacementDepartmentsRepo;
import com.pcw.demo.repository.PlacementDetailsRepository;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class PlacementDetailsController {
	@Autowired
	private EligiblePlacementDepartmentsRepo epdRepo;
	@Autowired
	private PlacementDetailsRepository pdRepo;
	
	@PostMapping("/add-eligible-department")
	public EligiblePlacementDepartments addEligiblePlacementDepartment(@RequestBody EligiblePlacementDepartments epd) {
		return epdRepo.save(epd);
	}
	
	@GetMapping("/get-placementdetails-by-department")
	public List<PlacementDetails> viewPlacementDetails(@RequestParam int departmentid){
		List l=epdRepo.findByDepartmentid(departmentid);
		
		List<PlacementDetails> d=new ArrayList<PlacementDetails>();
		for(int i=0;i<l.size();i++) {
			
			EligiblePlacementDepartments obj=(EligiblePlacementDepartments) l.get(i);
			System.out.println(obj.getPid());
			PlacementDetails pd=(PlacementDetails) pdRepo.findByPid(Long.valueOf(obj.getPid()));
			d.add(pd);
		}
		return d;
		//get pids from eligible department and make an arraylist
		//loop throug the arraylist and find details with each pid and append to another arraylist
	}

}
