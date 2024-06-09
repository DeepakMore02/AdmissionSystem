package com.service;

import java.util.List;
import java.util.Map;

import com.model.Course;

public interface CourseService
{
	public Course save(Course course);
	
	public List<Course> getAll();
	
	public long countCourse();
	
	public long countByStatus(String status);
}
