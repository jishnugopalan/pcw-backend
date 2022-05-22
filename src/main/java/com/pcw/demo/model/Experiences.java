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
public class Experiences {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long experienceid;
	private String experience;

}
