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
@AllArgsConstructor
@NoArgsConstructor
@Table(name="batches")
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int batchid;
	private int departmentid;
	private String batchname;

}
