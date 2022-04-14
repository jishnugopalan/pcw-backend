package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
