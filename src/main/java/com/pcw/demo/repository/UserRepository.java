package com.pcw.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);
	User findByUsernameAndPassword(String username,String password);
	User findById(Long id);
	

	

}
