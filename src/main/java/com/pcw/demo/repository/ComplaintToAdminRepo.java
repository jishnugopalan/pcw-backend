package com.pcw.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcw.demo.model.ComplaintToAdmin;

public interface ComplaintToAdminRepo extends JpaRepository<ComplaintToAdmin, Long>{

}
