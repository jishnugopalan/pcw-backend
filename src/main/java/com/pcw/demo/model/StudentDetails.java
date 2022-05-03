package com.pcw.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="student_details")
public class StudentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentid;
	private Long userid;
	private int departmentid;
	private Long registration_number;
	private String housename;
	private String place;
	private String district;
	private String state;
	private String country;
	private Long pincode;
	private String sslc;
	private String plustwo;
	private String ug;
	private String idproof;
	private String photo;
	private int sslcpercentage;
	private int plustwopercentage;
	private int ugpercentage;
	private String gender;
	private String date_of_birth;
	private String batchid;
	private String academic_starting_year;
	private String academic_ending_year;
	

}
