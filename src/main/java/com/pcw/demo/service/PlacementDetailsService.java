package com.pcw.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pcw.demo.model.PlacementDetails;
import com.pcw.demo.repository.PlacementDetailsRepository;

@Service
public class PlacementDetailsService {
	@Autowired
	private PlacementDetailsRepository placementRepo;
	
	public PlacementDetails addPlacementDetails(PlacementDetails pls) {
		return placementRepo.save(pls);
	}
	
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
	 public List<PlacementDetails> viewAllDetails() {
		 return placementRepo.findAll();
	 }
	 

}
