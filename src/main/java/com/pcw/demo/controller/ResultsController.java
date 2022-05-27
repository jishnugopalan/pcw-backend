package com.pcw.demo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pcw.demo.model.Results;
import com.pcw.demo.payload.response.MessageResponse;
import com.pcw.demo.repository.ResultsRepo;
import com.pcw.demo.service.ResultsService;
@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class ResultsController {

	@Autowired
	private ResultsService resultService;
	@Autowired
	private ResultsRepo resultRepo;
	@PostMapping("/add-result")
	public Results addResults(@RequestParam String result_subject,
			@RequestParam String result_description,
			@RequestPart("result_file") MultipartFile file
			) {
		Results result=new Results();
		String uploadDir = "uploads/results";
		resultService.saveFile(file,uploadDir);
		String result_file=uploadDir+"/"+file.getOriginalFilename();
		result.setResult_subject(result_subject);
		result.setResult_description(result_description);
		result.setResult_file(result_file);
		return resultRepo.save(result);
		
	}
	@GetMapping("/get-all-result")
	public List<Results> getAllResults(){
		return resultRepo.findAll();
	}
	@GetMapping("/get-results-by-id")
	public Optional<Results> findResultById(@RequestParam Long resultid) {
		Optional<Results> result=resultRepo.findById(resultid);
		
		if(result.isPresent()) {
			return result;
		}
		else {
			return null;
		}
	}
	@DeleteMapping("/delete-result")
	public ResponseEntity<?> deleteResult(@RequestParam Long resultid) throws IOException{
		Results result=resultRepo.findByResultid(resultid);
		
		Path oldPath = Paths.get(result.getResult_file());
		FileSystemUtils.deleteRecursively(oldPath);
		resultRepo.deleteById(resultid);
		return ResponseEntity.ok(new MessageResponse("Deletd successfully"));
		
	}
}
