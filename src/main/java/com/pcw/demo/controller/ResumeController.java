package com.pcw.demo.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcw.demo.model.Certifications;
import com.pcw.demo.model.Education;
import com.pcw.demo.model.Experiences;
import com.pcw.demo.model.Hobbies;
import com.pcw.demo.model.Projects;
import com.pcw.demo.model.Resume;
import com.pcw.demo.model.Skills;
import com.pcw.demo.model.Summary;
import com.pcw.demo.repository.CertificationsRepo;
import com.pcw.demo.repository.EducationRepo;
import com.pcw.demo.repository.ExperiencesRepo;
import com.pcw.demo.repository.HobbiesRepo;
import com.pcw.demo.repository.ProjectsRepo;
import com.pcw.demo.repository.ResumeRepository;
import com.pcw.demo.repository.SkillsRepo;
import com.pcw.demo.repository.SummaryRepo;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
public class ResumeController {

	@Autowired
	private ResumeRepository resumeRepo;
	@Autowired
	private SkillsRepo skillsRepo;
	@Autowired
	private CertificationsRepo certificationRepo;
	@Autowired
	private ExperiencesRepo experienceRepo;
	@Autowired
	private HobbiesRepo hobbiesRepo;
	@Autowired
	private ProjectsRepo projectsRepo;
	@Autowired
	private SummaryRepo summaryRepo;
	@Autowired
	private EducationRepo educationRepo;
	
	
	@PostMapping("/add-resume")
	public Resume addResume(@RequestBody Resume resume) {
		System.out.println(resume);
		Long resumeid=resumeRepo.findByUserid(resume.getUserid());
		if(resumeid!=null) {
			return null;
		}
	    Summary summary=resume.getSummary();
	    summaryRepo.save(summary);
		Set<Skills> skills=resume.getSkills();
		Set<Certifications> certifications=resume.getCertifications();
		Set<Experiences>experiences=resume.getExperiences();
		Set<Hobbies>hobbies=resume.getHobbies();
		Set<Projects>projects=resume.getProjects();
		Set<Education>education=resume.getEducation();
		
		
		System.out.println(skills);
		Skills x=new Skills();
		for (Skills temp : skills) {
			x=temp;
	        skillsRepo.save(x);
	     }
		for (Certifications temp : certifications) {
			
	        certificationRepo.save(temp);
	     }
        for (Experiences temp : experiences) {
			
	        experienceRepo.save(temp);
	     }
        for (Hobbies temp : hobbies) {
			
	        hobbiesRepo.save(temp);
	     }
        for (Projects temp : projects) {
			
	        projectsRepo.save(temp);
	     }
        for (Education temp : education) {
			
	       educationRepo.save(temp);
	     }
		
		return resumeRepo.save(resume);
		//return null;
		
	}
	
	@GetMapping("/get-resume-details")
	public Optional<Resume> getResumeDetails(@RequestParam Long userid) {
		Long resumeid=resumeRepo.findByUserid(userid);
		System.out.println(resumeid);
		System.out.println(resumeRepo.findById(resumeid));
		return resumeRepo.findById(resumeid);
	}
}
