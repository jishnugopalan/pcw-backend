package com.pcw.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Skills {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skillid;
    private String skill;
    
    public Long getSkillid() {
		return skillid;
	}
	public void setSkillid(Long skillid) {
		this.skillid = skillid;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
}
