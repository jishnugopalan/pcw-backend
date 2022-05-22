package com.pcw.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certifications {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long certificateid;
	private String certificate;

}
