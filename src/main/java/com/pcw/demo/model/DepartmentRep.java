package com.pcw.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(	name = "department_rep")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRep {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rep_id;
	private int departmentid;
	private Long id;

}
