package com.pcw.demo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.pcw.demo.repository.StudentDetailsRepository;
import com.pcw.demo.service.EmailSenderService;

@SpringBootApplication
public class PcwApplication {
	@Resource
	StudentDetailsRepository storageService;
	@Autowired
	private EmailSenderService service;

	public static void main(String[] args) {
		SpringApplication.run(PcwApplication.class, args);
	}
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail(){
//		
//    service.sendSimpleMail("jishnugopalan2000@gmail.com", "My demo email", "Demo email");
//    		
//	}
			                         
	 
	

}
