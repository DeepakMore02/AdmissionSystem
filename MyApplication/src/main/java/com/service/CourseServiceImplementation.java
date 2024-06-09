package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CourseRepository;
import com.model.Course;

@Service
public class CourseServiceImplementation implements CourseService
{
	@Autowired
	CourseRepository courseRepo;
	

	@Override
	public Course save(Course course)
	{
		Course findCourse = courseRepo.findByCname(course.getCname());
		Course c = new Course();
		if(findCourse == null)
		{
			c = courseRepo.save(course);
			return c;
		}
		
			
		return c;
	}

	@Override
	public List<Course> getAll()
	{
		List<Course> courseList = courseRepo.findAll();
		return courseList;
	}

	@Override
	public long countCourse()
	{
		long count = courseRepo.count();
		return count;
	}

	@Override
	public long countByStatus(String status)
	{
		long count = courseRepo.countByStatus(status);
		return count;
	}

	
}
