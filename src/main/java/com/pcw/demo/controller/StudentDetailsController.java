package com.pcw.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


import com.pcw.demo.model.StudentDetails;

import com.pcw.demo.service.StudentDetailsService;
import com.pcw.demo.upload.message.ResponseMessage;

import antlr.StringUtils;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class StudentDetailsController {
	
	
	
	@Autowired
	private StudentDetailsService stdservice;
	@PostMapping("/addstudentdetails")
	  public ResponseEntity<ResponseMessage> uploadFile(
			  @RequestParam("userid") String userid,
			  @RequestParam("departmentid") String departmentid,
			  @RequestParam("registration_number") String registration_number,
			  @RequestParam("house") String house,
			  @RequestParam("place") String place,
			  @RequestParam("district") String district,
			  @RequestParam("state") String state,
			  @RequestParam("country") String country,
			  @RequestParam("pincode") String pincode,
			  @RequestPart("sslc") MultipartFile file1,
			  @RequestPart("plustwo") MultipartFile file2,
			  @RequestPart("ug") MultipartFile file3,
			  @RequestPart("idproof") MultipartFile file4,
			  @RequestPart("photo") MultipartFile file5,
			  @RequestParam("sslcpercentage") String sslcpercentage,
			  @RequestParam("plustwopercentage") String plustwopercentage,
			  @RequestParam("ugpercentage") String ugpercentage
			 ) {
	    String message = "";
	    try {
	    	StudentDetails student=new StudentDetails();
	    	System.out.println(userid);
	    	System.out.println(file1.getOriginalFilename());
	    	String uploadDir = "uploads/" + userid;
	    	
	    	stdservice.saveFile(file1,uploadDir);
	    	stdservice.saveFile(file2,uploadDir);
	    	stdservice.saveFile(file3,uploadDir);
	    	stdservice.saveFile(file4,uploadDir);
	    	stdservice.saveFile(file5,uploadDir);
	    	
	    	String sslc=uploadDir+"/"+file1.getOriginalFilename();
	    	String plustwo=uploadDir+"/"+file2.getOriginalFilename();
	    	String ug=uploadDir+"/"+file3.getOriginalFilename();
	    	String idproof=uploadDir+"/"+file4.getOriginalFilename();
	    	String photo=uploadDir+"/"+file5.getOriginalFilename();
	    	
	    	student.setUserid(Long.valueOf(userid));
	    	student.setDepartmentid(Integer.parseInt(departmentid));
	    	student.setRegistration_number(Long.valueOf(registration_number));
	    	student.setHousename(house);
	    	student.setPlace(place);
	    	student.setDistrict(district);
	    	student.setState(state);
	    	student.setCountry(country);
	    	student.setPincode(Long.valueOf(pincode));
	    	student.setSslc(sslc);
	    	student.setPlustwo(plustwo);
	    	student.setUg(ug);
	    	student.setIdproof(idproof);
	    	student.setPhoto(photo);
	    	student.setSslcpercentage(Integer.parseInt(sslcpercentage));
	    	student.setPlustwopercentage(Integer.parseInt(plustwopercentage));
	    	student.setUgpercentage(Integer.parseInt(ugpercentage));
	    	
	    	stdservice.addStudentDetails(student);
	    	
	    	

	      message = ("Student details added successfully");
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	    	e.printStackTrace();
	      //message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      //return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("exception occured"));
	    }
	  }
	@GetMapping("/viewstudentdetailsbyid")
	public StudentDetails viewStudentById(@RequestParam("userid") Long userid) throws Exception {
		
		StudentDetails obj= stdservice.viewStudentById(userid);
		if(obj==null) {
			throw new Exception("User not found");
		}
		else
			return obj;
	}

//	  @GetMapping("/files")
//	  public ResponseEntity<List<StudentDetails>> getListFiles() {
//	    List<StudentDetails> fileInfos = stdservice.loadAll().map(path -> {
//	      String filename = path.getFileName().toString();
//	      String url = MvcUriComponentsBuilder
//	          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
//
//	      return new FileInfo(filename, url);
//	    }).collect(Collectors.toList());
//
//	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
//	  }
//
	  @GetMapping("/files/{filename:.+}")
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		  System.out.println(filename);
	    Resource file = stdservice.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	  
	  @GetMapping("/getphoto")
	  public ResponseEntity<Resource> getPhoto(@RequestParam("filename") String filename) {
		  System.out.println(filename);
	    Resource file = stdservice.getPhoto(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	  
	  

}
