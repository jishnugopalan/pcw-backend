package com.pcw.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.Role;
import com.pcw.demo.model.User;
import com.pcw.demo.service.UserService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@PostMapping("/adduserrole")
	public Role addRole(@RequestBody Role role) {
		return userService.addUserRole(role);
	}
	
	
	@PostMapping("/addstudent")
	public User addUser(@RequestBody User user) {
		return userService.registerStudent(user);
	}
	
	@GetMapping("/getuserroles")
	public List<Role> getRoles() {
		return userService.getRoles();
	}
	
	
	
	

}
