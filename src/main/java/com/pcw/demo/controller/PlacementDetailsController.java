package com.pcw.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pcw.demo.model.Department;
import com.pcw.demo.model.EligiblePlacementDepartments;
import com.pcw.demo.model.PlacementDetails;
import com.pcw.demo.repository.EligiblePlacementDepartmentsRepo;
import com.pcw.demo.repository.PlacementDetailsRepository;
import com.pcw.demo.service.DepartmentService;
import com.pcw.demo.service.PlacementDetailsService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class PlacementDetailsController {
	@Autowired
	private EligiblePlacementDepartmentsRepo epdRepo;
	@Autowired
	private PlacementDetailsRepository pdRepo;
	@Autowired
	private PlacementDetailsService pdService;
	@Autowired
	private DepartmentService depservice;
	
	//Add eligible department for placement 
	@PostMapping("/add-eligible-department")
	public EligiblePlacementDepartments addEligiblePlacementDepartment(@RequestBody EligiblePlacementDepartments epd) {
		return epdRepo.save(epd);
	}
	
	//delete eligible department
	@PostMapping("/delete-eligible-department")
	public EligiblePlacementDepartments deleteEligibleDepartment(@RequestParam String departmentid,@RequestParam String pid) {
		System.out.println(epdRepo.deleteEligibleDep(Integer.parseInt(pid), Integer.parseInt(departmentid)));
		EligiblePlacementDepartments obj=epdRepo.deleteEligibleDep(Integer.parseInt(pid), Integer.parseInt(departmentid));
		epdRepo.delete(obj);
		
		return obj;
	}
	
	//get department not in eligible department of a placement
	@GetMapping("/get-department-notin-eligible")
	public List getDepartmentNotInEligibleDep(@RequestParam int pid) {
		return epdRepo.getDepartmentNotinElgibleDep(pid);
	}
	
	//get eligible department list by placement id
	@GetMapping("/view-eligible-department-by-pid")
	public List getEligibleDepartmentByPid(@RequestParam int pid) {
		List<Department> deps = new ArrayList<>();
		List edep= epdRepo.findByPid(pid);
		for(int i=0;i<edep.size();i++) {
		   EligiblePlacementDepartments obj=(EligiblePlacementDepartments) edep.get(i);
		   Department depobj=depservice.getDepartmentById(obj.getDepartmentid());
		   deps.add(depobj);		   
		}
		return deps;
	}
	
	
	
	//Get placement details by department id
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
	
	//Get all placement details
	@GetMapping("/view-all-placementdetails")
	public List<PlacementDetails> viewAllPlacementDetails(){
		return pdService.viewAllDetails();
	}
	//delete a placement details
	@DeleteMapping("/delete-placementdetails")
	public void deletePlacementDetails(Long pid) {
		pdService.deletePlacementDetails(pid);
	 }
	
	@GetMapping("/view-placement-details-byid")
	public PlacementDetails viewPlacementDetailsById(@RequestParam Long pid) {
		 return pdService.viewPlacementDetailsById(pid);
	 }
	
	
	

}
