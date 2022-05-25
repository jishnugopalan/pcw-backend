package com.pcw.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pcw.demo.model.StudentDetails;

public interface StudentRepository extends JpaRepository<StudentDetails, Integer>{
	StudentDetails findByUserid(Long userid);
	int deleteByUserid(Long userid);
	@Query(value="select student_details.studentid,student_details.isverified,users.fullname,users.id from student_details inner join users on student_details.userid=users.id\r\n"
			+ "where student_details.departmentid=?1",nativeQuery = true)
	public List getStudentDetailsByDepartment(int departmentid);
    public List findByDepartmentid(int departmentid);
}
