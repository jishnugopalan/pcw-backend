package com.pcw.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.Role;
import com.pcw.demo.model.User;
import com.pcw.demo.repository.UserRepository;
import com.pcw.demo.service.UserService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class UserController {
	
	@Autowired
	public UserService userService;
	@Autowired
	public UserRepository userRepo;
	
	@GetMapping("/userdata")
	public Optional<User> getUser(@RequestParam String email) {
		return userService.getUser(email);
	}
	
	@GetMapping("/get-user-data")
	public User getUserDetails(@RequestParam Long id) {
		return userRepo.findById(id);
	}
	
	
	


	
	
	
	

}
