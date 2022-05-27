package com.pcw.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.Complaint;
import com.pcw.demo.model.ComplaintReply;
import com.pcw.demo.model.ComplaintToAdmin;
import com.pcw.demo.model.StudentDetails;
import com.pcw.demo.model.User;
import com.pcw.demo.payload.response.MessageResponse;
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
	@GetMapping("/get-complaint-by-id")
	public Complaint getComplaintById(@RequestParam Long complaintid) {
		return complaintRepo.findByComplaintid(complaintid);
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
			List c=complaintRepo.findByUser(user);
			for(int j=0;j<c.size();j++) {
				System.out.println(c.get(j));
				Complaint cmp=(Complaint) c.get(j);
				complaints.add(cmp);
				
			}
			
		}
		return complaints;
//		User user=userRepo.findById(userid);
//		System.out.println(user);
//		return complaintRepo.findByUser(user);
   }
	@PostMapping("/add-complaint-replay")
	public ResponseEntity<?> addReplay(@RequestBody ComplaintReply complaintreplay) {
		Complaint complaint=complaintreplay.getComplaint();
		Optional<Complaint> c=Optional.ofNullable(complaintreplay.getComplaint());
		ComplaintReply comreply= complaintReplayRepo.findByComplaint(c);
		System.out.println(comreply);
		if(comreply!=null) {
			return ResponseEntity.ok(new MessageResponse("Already replied"));
		}
		else {
			Complaint cmp=complaintRepo.findByComplaintid(complaint.getComplaintid());
			cmp.setComplaint_status("replied");
			complaintRepo.save(cmp);
			complaintReplayRepo.save(complaintreplay);
			return ResponseEntity.ok(new MessageResponse("Replied successfully"));
		}
		
		
	}
	@GetMapping("/get-complaint-reply")
	public ComplaintReply getComplaintReplay(@RequestParam Long complaintid) {
		Optional<Complaint> c=complaintRepo.findById(complaintid);
		if(c.isPresent()) {
			return complaintReplayRepo.findByComplaint(c);
		}
		else
			return null;
	}
	@PostMapping("/add-admin-to-complaint")
	public ResponseEntity<?> addAdminToComplaint(@RequestBody ComplaintToAdmin complainttoadmin) {
		ComplaintToAdmin cmptoadmin=complaintToAdminRepo.findByComplaint(complainttoadmin.getComplaint());
        if(cmptoadmin==null) {
        	Complaint complaint=complainttoadmin.getComplaint();
    		Complaint cmp=complaintRepo.findByComplaintid(complaint.getComplaintid());
    		cmp.setComplaint_status("send to admin");
    		complaintRepo.save(cmp);
    		complaintToAdminRepo.save(complainttoadmin);
    		return ResponseEntity.ok(new MessageResponse("Complaint send to admin"));
        }
        else {
        	return ResponseEntity.ok(new MessageResponse("This complaint is already send to admin"));
        }
		
		
	}
	@GetMapping("/get-admin-to-complaint")
	public List<ComplaintToAdmin> getComplaintToAdmin() {
		return complaintToAdminRepo.findAll();
	}
	
}
