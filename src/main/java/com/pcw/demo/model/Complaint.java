package com.pcw.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long complaintid;
	private String complaint_sub;
	@Column(length=1000)
	private String complaint;
	@Column(name="timestamp")
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	private String complaint_status="not replaied";
	@ManyToOne
	private User user;
	

}
