package com.pcw.demo.repository;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.pcw.demo.model.StudentDetails;

public interface StudentDetailsRepository{


	 public void init();

	  public void saveFile(MultipartFile file,String uploadDir);

	  public Resource load(String filename);
	  
	  public Resource getPhoto(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();

}
