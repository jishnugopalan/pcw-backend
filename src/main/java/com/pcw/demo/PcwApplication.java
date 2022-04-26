package com.pcw.demo;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.pcw.demo.repository.StudentDetailsRepository;

@SpringBootApplication
public class PcwApplication {
	@Resource
	  StudentDetailsRepository storageService;

	public static void main(String[] args) {
		SpringApplication.run(PcwApplication.class, args);
	}
	
	 
	

}
