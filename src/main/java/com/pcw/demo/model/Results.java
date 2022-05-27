package com.pcw.demo.model;

import java.sql.Timestamp;

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
public class Results {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resultid;
	private String result_subject;
	private String result_description;
	private String result_file;
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

}
