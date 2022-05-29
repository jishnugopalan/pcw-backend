package com.pcw.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pcw.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);
	User findByUsernameAndPassword(String username,String password);
	User findById(Long id);
	@Query(value="select id,fullname from users where id in(select userid from student_details where departmentid=?1)",nativeQuery = true)
    List getUsersByDepartment(int departmentid);
	
	@Query(value="select * from users where id in(select id from department_rep where departmentid=?1)",nativeQuery = true)
	List getDepartmentRepByDepartmentid(int departmentid);
	
	@Query(value="select * from users where id in(select id from department_rep)",nativeQuery = true)
	List getAllDepartmentRep();
	
	@Query(value="select department from departments where departmentid in(select departmentid from department_rep where id=?1)",nativeQuery = true)
	List getDepartmentByUserid(Long id);

	Optional<User> findByPhone(Long phone);

	

	

}
