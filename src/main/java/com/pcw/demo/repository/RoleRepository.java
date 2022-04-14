package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByName(String name);

}
