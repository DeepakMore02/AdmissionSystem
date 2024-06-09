package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>
{

	Student findByName(String name);

	Student findByEmail(String email);
	
	Student findByMobile(String mobile);
	
	 List<Student> findAllByStatusOrderByNameAsc(String status);
	 
	 @Query("SELECT s FROM Student s WHERE s.course = :course AND s.batch IS NULL")
	 ArrayList<Student> findStudentsByCourseAndBatchIdIsNull(String course);
	 
	 long countByStatus(String status);
	 
	 long count();
}
