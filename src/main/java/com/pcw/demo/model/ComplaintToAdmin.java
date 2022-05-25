package com.pcw.demo.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintToAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long admincomplaintid;
	@OneToOne
	private Complaint complaint;
}
