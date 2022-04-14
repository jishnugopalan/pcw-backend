package com.pcw.demo.model;

import javax.persistence.Column;
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
@Table(name="roles")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roleid")
    private Integer id;
     
    @Column(nullable = false, length = 45)
    private String name;

}
