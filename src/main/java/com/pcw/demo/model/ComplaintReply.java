package com.pcw.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintReply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long replyid;
	@Column(length=1000)
	private String reply;
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	@OneToOne
	Complaint complaint;
	@ManyToOne
	private User user;
}
