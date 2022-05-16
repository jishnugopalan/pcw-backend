package com.pcw.demo.service;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pcw.demo.model.Role;
import com.pcw.demo.model.User;
import com.pcw.demo.repository.RoleRepository;
import com.pcw.demo.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private RoleRepository roleRepository;
	
	//Add department representative
	public User addDepartmentRep(Long id) {
		User rep=userrepo.findById(id);
		 Set<Role> roles=rep.getRoles();
		 Role repRole = roleRepository.findByName("representative");
		 roles.add(repRole);
		 rep.setRoles(roles);
		 userrepo.save(rep);
		 return rep;
	}
	
	//Save a new document
	 public void saveFile(MultipartFile file,String uploadDir) {
		    try {
		    	Path uploadPath = Paths.get(uploadDir);
		          
		        if (!Files.exists(uploadPath)) {
		            Files.createDirectories(uploadPath);
		        }
		    	System.out.println("in savefile");
		      Files.copy(file.getInputStream(), uploadPath.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
		      System.out.println("in savefile2");
		    } catch (Exception e) {
		      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		    }
		  }
	

}
