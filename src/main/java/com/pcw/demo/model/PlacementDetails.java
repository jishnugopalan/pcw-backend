package com.pcw.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="placement_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacementDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	private String companyname;
	private double ctc;
	private String designantion;
	private String description;
	private String registration_link;
	private String registration_start_date;
	private String registration_end_date;
	private String job_description_file;
	
	
	

}
