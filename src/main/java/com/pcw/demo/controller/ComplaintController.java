package com.pcw.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.Complaint;
import com.pcw.demo.model.ComplaintReplay;
import com.pcw.demo.model.ComplaintToAdmin;
import com.pcw.demo.model.StudentDetails;
import com.pcw.demo.model.User;
import com.pcw.demo.repository.ComplaintReplayRepo;
import com.pcw.demo.repository.ComplaintRepo;
import com.pcw.demo.repository.ComplaintToAdminRepo;
import com.pcw.demo.repository.StudentRepository;
import com.pcw.demo.repository.UserRepository;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class ComplaintController {
	@Autowired
	private ComplaintRepo complaintRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private ComplaintReplayRepo complaintReplayRepo;
	@Autowired
	private ComplaintToAdminRepo complaintToAdminRepo;

	@PostMapping("/add-complaint")
	public Complaint addComplaint(@RequestBody Complaint complaint) {
		//User user=complaint.getUser();
		return complaintRepo.save(complaint);
	}
	@GetMapping("/get-complaints")
	public List<Complaint> getComplaint(@RequestParam Long userid) {
		System.out.println(userid);
		User user=userRepo.findById(userid);
		System.out.println(user);
		return complaintRepo.findByUser(user);
   }
	@GetMapping("/get-complaints-by-departmentid")
	public List<Complaint> getComplaintByDepartmentid(@RequestParam int departmentid) {
		List students=studentRepo.findByDepartmentid(departmentid);
		System.out.println(students);
		List<Long> userid = new ArrayList<Long>();
		List<Complaint> complaints = new ArrayList<Complaint>();
		for(int i=0;i<students.size();i++) {
			StudentDetails s=(StudentDetails) students.get(i);
			userid.add(s.getUserid());
		}
		System.out.println(userid);
		for(int i=0;i<userid.size();i++) {
			Long uid=userid.get(i);
			User user=userRepo.findById(uid);
			System.out.println(user);
			System.out.println(complaintRepo.findByUser(user));
			List<Complaint>c=complaintRepo.findByUser(user);
			for(int j=0;j<c.size();j++) {
				Complaint cmp=c.get(i);
				complaints.add(cmp);
				
			}
			
		}
		return complaints;
//		User user=userRepo.findById(userid);
//		System.out.println(user);
//		return complaintRepo.findByUser(user);
   }
	@PostMapping("/add-complaint-replay")
	public ComplaintReplay addReplay(@RequestBody ComplaintReplay complaintreplay) {
		Complaint complaint=complaintreplay.getComplaint();
		Complaint c=complaintRepo.findByComplaintid(complaint.getComplaintid());
		c.setComplaint_status("replied");
		complaintRepo.save(c);
		return complaintReplayRepo.save(complaintreplay);
	}
	@GetMapping("/get-complaint-repaly")
	public ComplaintReplay getComplaintReplay(@RequestParam Long complaintid) {
		Optional<Complaint> c=complaintRepo.findById(complaintid);
		if(c.isPresent()) {
			return complaintReplayRepo.findByComplaint(c);
		}
		else
			return null;
	}
	@PostMapping("/add-admin-to-complaint")
	public ComplaintToAdmin addAdminToComplaint(@RequestBody ComplaintToAdmin complainttoadmin) {
		return complaintToAdminRepo.save(complainttoadmin);
	}
	@GetMapping("/get-admin-to-complaint")
	public List<ComplaintToAdmin> getComplaintToAdmin() {
		return complaintToAdminRepo.findAll();
	}
	
}
