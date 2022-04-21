//package com.pcw.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pcw.demo.model.Role;
//import com.pcw.demo.model.User;
//import com.pcw.demo.service.UserService;
//
//@CrossOrigin(origins="http://localhost:4200/")
//@RestController
//public class UserController {
//	
//	@Autowired
//	public UserService userService;
//	
//	@PostMapping("/adduserrole")
//	public Role addRole(@RequestBody Role role) {
//		return userService.addUserRole(role);
//	}
//	
//	
//	@PostMapping("/addstudent")
//	public User addUser(@RequestBody User user) throws Exception{
//		String email=user.getEmail();
//		long phone = user.getPhone();
//		User userobj=userService.fetchUserByEmaiId(email);
//		User userobj1=userService.findByPhone(phone);
//		if (userobj1 != null) {
//			throw new Exception("User with phone number"+user.getPhone()+" already exist");
//		}
//		if(userobj!=null) {
//		   throw new Exception("User with "+user.getEmail()+" is already exist");
//		}
//		return userService.registerStudent(user);
//	}
//	
//	@PostMapping("/login")
//	@CrossOrigin(origins="http://localhost:4200/")
//	public User loginUser(@RequestBody User user) throws Exception{
//		
//		User userobj1=userService.fetchUserByEmaiId(user.getEmail());
//		if(userobj1!=null) {
//			User userobj2=userService.fetchUserByEmailAndPassword(user.getEmail(),user.getPassword());
//			if(userobj2==null) {
//				throw new Exception("Incorrect Password");
//			}
//			else {
//				return userobj2;
//			}
//		}
//		else {
//			throw new Exception("User not found");
//		}
//		
//		
//		
//	}
//	
//	@GetMapping("/getuserroles")
//	public List<Role> getRoles() {
//		return userService.getRoles();
//	}
//	
//	
//	
//	
//	
//	
//
//}
