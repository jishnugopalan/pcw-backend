package com.pcw.demo.controller;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pcw.demo.model.PlacementDetails;
import com.pcw.demo.model.User;
import com.pcw.demo.service.AdminService;
import com.pcw.demo.service.PlacementDetailsService;
import com.pcw.demo.upload.message.ResponseMessage;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private PlacementDetailsService plsService;
	
//	@Before(value = "")
//	public void 
	
	@PostMapping("/add-department-rep")
	public User addDepartmentRep(@RequestParam Long id) {
		return adminService.addDepartmentRep(id);
	}
	
	@PostMapping("/add-palcement-details")
	public PlacementDetails addPlacementDetails(@RequestParam("companyname") String companyname,
			
			@RequestParam("ctc")String ctc,
			@RequestParam("designation") String designation,
			@RequestParam("description") String description,
			@RequestParam("registration_link")String registration_link,
			@RequestParam("registration_start_date")String registration_start_date,
			@RequestParam("registration_end_date")String registration_end_date,
			@RequestPart("job_description_file")MultipartFile file) {
		try {
			String uploadDir = "uploads/jobdesfiles";
			String job_description_file=uploadDir+"/"+file.getOriginalFilename();
			adminService.saveFile(file, uploadDir);
			
			PlacementDetails pls=new PlacementDetails();
			pls.setCompanyname(companyname);
			
			pls.setCtc(Double.parseDouble(ctc));
			pls.setDescription(description);
			pls.setDesignantion(designation);
			pls.setRegistration_link(registration_link);
			pls.setRegistration_start_date(registration_start_date);
			pls.setRegistration_end_date(registration_end_date);
			pls.setJob_description_file(job_description_file);
			
			
			 String message = ("Placement details added successfully");
		      return plsService.addPlacementDetails(pls);
		    } catch (Exception e) {
		    	e.printStackTrace();
		      //message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		      //return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		    	return null;
		    }
		
	}

}
