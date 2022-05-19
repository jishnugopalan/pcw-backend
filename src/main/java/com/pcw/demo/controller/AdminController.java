package com.pcw.demo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pcw.demo.model.DepartmentRep;
import com.pcw.demo.model.PlacementDetails;
import com.pcw.demo.model.User;
import com.pcw.demo.repository.DepartmentRepRepository;
import com.pcw.demo.repository.PlacementDetailsRepository;
import com.pcw.demo.repository.UserRepository;
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
	@Autowired
	private PlacementDetailsRepository plsrepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DepartmentRepRepository deprep;
	
 

	//Add placement details
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
			String uploadDir = "uploads/jobdesfiles/"+companyname.toLowerCase().trim();
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
	//add department rep
	@PostMapping("/add-department-rep")
	public User addDepartmentRep(@RequestParam Long id,@RequestParam int departmentid) {
		DepartmentRep drep=new DepartmentRep();
		drep.setDepartmentid(departmentid);
		drep.setId(id);
		deprep.save(drep);
		return adminService.addDepartmentRep(id);
	}
	
	@GetMapping("/get-userby-department")
	public List getUserByDepartmentid(@RequestParam int departmentid) {
		return userRepo.getUsersByDepartment(departmentid);
	}
	
	//update placement details
	@PostMapping("/update-placement-details")
	public PlacementDetails updatePalcementDetails(
			@RequestParam("pid")String pid,
			@RequestParam("companyname") String companyname,
			@RequestParam("ctc")String ctc,
			@RequestParam("designation") String designation,
			@RequestParam("description") String description,
			@RequestParam("registration_link")String registration_link,
			@RequestParam("registration_start_date")String registration_start_date,
			@RequestParam("registration_end_date")String registration_end_date) {
		Long _pid=Long.valueOf(pid);
		PlacementDetails obj=plsrepo.findByPid(_pid);
		System.out.println(obj);
		System.out.println(companyname);
		obj.setCompanyname(companyname);
		System.out.println(obj);
		
		
		obj.setCtc(Double.parseDouble(ctc));
		obj.setDescription(description);
		obj.setDesignantion(designation);
		obj.setRegistration_link(registration_link);
		obj.setRegistration_start_date(registration_start_date);
		obj.setRegistration_end_date(registration_end_date);
		plsrepo.save(obj);
		return obj;
		
		
	}
	//update job description file
	@PostMapping("/update-job-description")
    public PlacementDetails updateJobDescription(@RequestParam String pid,@RequestPart("job_description_file")MultipartFile file) throws IOException {
    	Long _pid=Long.valueOf(pid);
		PlacementDetails obj=plsrepo.findByPid(_pid);
		String filename=obj.getJob_description_file();
		String companyname=obj.getCompanyname();
		Path oldPath = Paths.get(filename);
		FileSystemUtils.deleteRecursively(oldPath);
		String uploadDir = "uploads/jobdesfiles/"+companyname.toLowerCase().trim();
		String job_description_file=uploadDir+"/"+file.getOriginalFilename();
		adminService.saveFile(file, uploadDir);
		obj.setJob_description_file(job_description_file);
		plsrepo.save(obj);
		return obj;
		
	}
	
	//get all department rep
	@GetMapping("/get-all-department-rep")
	public List  getAllDepartmentRep() {
		System.out.println(userRepo.getAllDepartmentRep());
		return userRepo.getAllDepartmentRep();
	}
	@GetMapping("/get-department-by-userid")
	public List getDepartmentByuserid(@RequestParam Long id) {
		return userRepo.getDepartmentByUserid(id);
	}
	

}
