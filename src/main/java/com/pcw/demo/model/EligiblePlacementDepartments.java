package com.pcw.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EligiblePlacementDepartments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="epid")
	private int id;
	private int pid;
	private int departmentid;

}
