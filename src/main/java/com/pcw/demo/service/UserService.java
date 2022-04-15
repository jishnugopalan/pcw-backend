package com.pcw.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pcw.demo.model.Role;
import com.pcw.demo.model.User;
import com.pcw.demo.repository.RoleRepository;
import com.pcw.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private RoleRepository rolerepo;
	
	
	public Role addUserRole(Role role) {
		
		return rolerepo.save(role);
		
	}
	
	public User registerStudent(User user) {
        Role roleUser = rolerepo.findByName("student");
        user.addRole(roleUser);
 
        return userrepo.save(user);
    }
	public List<Role> getRoles() {
        return rolerepo.findAll();
    }


}
