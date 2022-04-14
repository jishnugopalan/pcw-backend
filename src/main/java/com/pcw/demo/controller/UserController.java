package com.pcw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.Role;
import com.pcw.demo.model.User;
import com.pcw.demo.service.UserService;

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
	
	
	
	

}
