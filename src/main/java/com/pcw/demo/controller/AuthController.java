package com.pcw.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.Role;
import com.pcw.demo.model.User;
import com.pcw.demo.payload.request.LoginRequest;
import com.pcw.demo.payload.request.SignupRequest;
import com.pcw.demo.payload.response.JwtResponse;
import com.pcw.demo.payload.response.MessageResponse;
import com.pcw.demo.repository.RoleRepository;
import com.pcw.demo.repository.UserRepository;
import com.pcw.demo.security.jwt.JwtUtils;
import com.pcw.demo.security.services.UserDetailsImpl;
import com.pcw.demo.service.UserService;


@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	public UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) throws Exception {
		System.out.println("in signin");
		if (userRepository.existsByUsername(loginRequest.getUsername())) {
			
		}else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Email Id Not Found"));
		}
		

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getFullname(),
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: Email is already taken!"));
	}

//	if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//		return ResponseEntity
//				.badRequest()
//				.body(new MessageResponse("Error: Email is already in use!"));
//	}
	else {
		System.out.println(signUpRequest.getPassword());
		System.out.println(encoder.encode(signUpRequest.getPassword()));
	}

	// Create new user's account
	
	User user = new User(signUpRequest.getUsername(),
			signUpRequest.getFullname(),
			signUpRequest.getPhone(),
		    encoder.encode(signUpRequest.getPassword()));

	Set<String> strRoles = signUpRequest.getRole();
	Set<Role> roles = new HashSet<>();

	if (strRoles == null) {
		Role userRole = roleRepository.findByName("student");
				
		roles.add(userRole);
	} else {
		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName("admin");
						
				roles.add(adminRole);

				break;
			case "student":
				Role modRole = roleRepository.findByName("student");
						
				roles.add(modRole);

				break;
			default:
				Role userRole = roleRepository.findByName("student");
						
				roles.add(userRole);
			}
		});
	}

	user.setRoles(roles);
	userRepository.save(user);

	return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}