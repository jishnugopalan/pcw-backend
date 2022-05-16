package com.pcw.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pcw.demo.model.Department;
import com.pcw.demo.model.StudentDetails;
import com.pcw.demo.repository.StudentDetailsRepository;
import com.pcw.demo.repository.StudentRepository;

@Service
public class StudentDetailsService  implements StudentDetailsRepository{
	@Autowired
	private StudentRepository std;
	
	private final Path root = Paths.get("uploads");
	
	//add student details
	public StudentDetails addStudentDetails(StudentDetails studentDetails) {	
		return std.save(studentDetails);
	}
	
	//view student details by userid
	public StudentDetails viewStudentById(Long userid) {
		return std.findByUserid(userid);
	}
	

	  @Override
	  public void init() {
	    try {
	      Files.createDirectory(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	  //save a document
	  @Override
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
	  
	  //delete a student details
	  public void deleteStudentDetails(int studentid) {
		  std.deleteById(studentid);
	  }

	  @Override
	  public Resource load(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }
	  
	  

	  @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }

	  @Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }

	@Override
	public Resource getPhoto(String filename) {
		// TODO Auto-generated method stub
		try {
		      Path file = Paths.get(filename);
		      Resource resource = new UrlResource(file.toUri());

		      if (resource.exists() || resource.isReadable()) {
		        return resource;
		      } else {
		        throw new RuntimeException("Could not read the file!");
		      }
		    } catch (MalformedURLException e) {
		      throw new RuntimeException("Error: " + e.getMessage());
		    }
	}
	

}
