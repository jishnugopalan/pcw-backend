package com.pcw.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.User;
import com.pcw.demo.payload.request.EmailRequest;
import com.pcw.demo.payload.response.MessageResponse;
import com.pcw.demo.repository.UserRepository;
import com.pcw.demo.service.EmailSenderService;
import com.pcw.demo.service.OtpService;
import com.pcw.demo.service.UserService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api/auth")
public class OtpController {

	@Autowired
	private OtpService otpService;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	public UserService userService;
	
	
	@PostMapping("/send-otp")
	public ResponseEntity<?> sendOtp(@RequestParam String email) {
		int otp = otpService.generateOTP(email);
		System.out.println(otpService.getOtp(email));
		emailSenderService.sendSimpleMail(email, 
				"Your verification code "+otp, 
				"Placement Cell Cusat");
		return ResponseEntity.ok(new MessageResponse("A verification code send to your mail id"));
	}
	
	@GetMapping("/verify-email")
	public ResponseEntity<?> validateEmail(@RequestParam String email) throws Exception {
		Optional<User> user=userRepo.findByUsername(email);
		if(user.isPresent()) {
			return ResponseEntity.ok(new MessageResponse("User exist"));
		}
		else {
			throw new Exception(email+" is not registered");
		}
	}
	@GetMapping("/verify-phone")
	public ResponseEntity<?> validatePhone(@RequestParam String phone) throws Exception {
		Optional<User> user=userRepo.findByPhone(Long.valueOf(phone));
		if(user.isPresent()) {
			return ResponseEntity.ok(new MessageResponse("User with "+phone+" is already exist"));
		}
		else {
			throw new Exception(phone+" is not registered");
		}
	}
	
	@GetMapping("/validate-otp")
	public ResponseEntity<?> validateOtp(@RequestParam String email,@RequestParam int otp) throws Exception {
		
		int orginal_otp=otpService.getOtp(email);
		if(orginal_otp==otp) {
			otpService.clearOTP(email);
			return ResponseEntity.ok(new MessageResponse("Verification successfull"));
		}
		else {
			throw new Exception("Please check your verification code");
		}
	}
	
	
	@GetMapping("/get-unknown-user")
	public Optional<User> getUser(@RequestParam String email) {
		return userService.getUser(email);
	}
}
