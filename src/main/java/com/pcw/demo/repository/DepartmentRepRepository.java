package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.DepartmentRep;

public interface DepartmentRepRepository extends JpaRepository<DepartmentRep, Integer>{
  public DepartmentRep findByDepartmentid(int departmentid);
}
