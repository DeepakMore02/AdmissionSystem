package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>
{
	Course findByCname(String name);
	
	long count();
	
	long countByStatus(String status);
}
