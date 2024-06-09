package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Course;
import com.service.CourseService;
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/course")
@RestController
public class CourseController
{
	@Autowired
	CourseService courseService;
	
	@PostMapping("/save")
	public ResponseEntity<Course> save(@RequestBody Course course)
	{
		Course c = courseService.save(course);
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Course>> getAll()
	{
		List<Course> courseList = courseService.getAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(courseList);
	}
	
//	@GetMapping("/get/{name}")
//	 public List<Course> getCourseNameByName(@PathVariable("name") String name)
//	{
//        return courseService.getCourseNameByName(name);
//    }
	
	@GetMapping("/countCourse")
	public ResponseEntity<Long> countCourse()
	{
		long count = courseService.countCourse();
		
		return ResponseEntity.status(HttpStatus.OK).body(count);
	}
	
	@GetMapping("/countByStatus/{status}")
	public ResponseEntity<Long> countByStatus(@PathVariable("status") String status)
	{
		long count = courseService.countByStatus(status);
		
		return ResponseEntity.status(HttpStatus.OK).body(count);
	}
}
