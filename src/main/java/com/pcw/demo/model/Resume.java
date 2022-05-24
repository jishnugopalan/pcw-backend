package com.pcw.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resume {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long resumeid;
	 private Long userid ;
	 @OneToOne(fetch=FetchType.EAGER)
	 private Summary summary;
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 private Set<Skills> skills=new HashSet<>();
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 private Set<Education> education=new HashSet<>();
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 private Set<Certifications> certifications=new HashSet<>();
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 private Set<Experiences> experiences=new HashSet<>();
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 private Set<Hobbies> hobbies=new HashSet<>();
	 
	 @OneToMany(fetch = FetchType.LAZY)
	 private Set<Projects> projects=new HashSet<>();
	
	
}
