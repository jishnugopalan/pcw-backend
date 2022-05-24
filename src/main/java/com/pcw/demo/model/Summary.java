package com.pcw.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Summary {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long summaryid;
	    @Column(length=1000)
	    private String summary;
	    

}
