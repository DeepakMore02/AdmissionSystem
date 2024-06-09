 package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Batch;
import com.model.Student;
import com.service.StudentService;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/student")
@RestController
public class StudentController
{
	@Autowired
	StudentService studentService;
	
	@PostMapping("/register")
	public ResponseEntity<Map<Boolean,String>> registerStudent(@RequestBody Student student)
	{
		Map<Boolean,String> msg = studentService.registerStudent(student);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(msg);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> addEnquiry(@RequestBody Student enquiry)
	{
		Object object = studentService.addEnquiry(enquiry);
		
		return ResponseEntity.status(HttpStatus.CREATED).header("Enquiry", "Enquiry Saved").body(object);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Student>> getAllEnquiry()
	{
		List<Student> enquries = studentService.getAllEnquiry();
		return ResponseEntity.status(HttpStatus.OK).header("Find", "Fetched All Enquiries").body(enquries);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<Object> getByName(@PathVariable("name") String name)
	{
		String n = name.toLowerCase();
		List<Student> student = studentService.getByName(n);
		if(student != null)
		{
			return ResponseEntity.status(HttpStatus.FOUND).header("FindById", "Fetched").body(student);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Such Student Exist");
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") int id)
	{
		Student student = studentService.getById(id);
		if(student != null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(student);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Such Student Exist");
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<Boolean,String>> deleteById(@PathVariable("id") int id)
	{
		Map<Boolean,String> map = studentService.deleteStudent(id);
		if(map.containsKey(true))
		{
			return ResponseEntity.status(HttpStatus.OK).body(map);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Student> update(@RequestBody Student student)
	{
		Student s = studentService.updateStudent(student);
		System.out.println("Inside Update Service");
		return ResponseEntity.status(HttpStatus.OK).body(s);		
	}
	
	@GetMapping("/sortByStatus/{status}")
	public ResponseEntity<List<Student>> sortByStatus(@PathVariable("status") String status)
	{
		List<Student> statusList = studentService.sortByStatus(status);
		
		return ResponseEntity.status(HttpStatus.OK).body(statusList);
	}
	
	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<Student> login(@PathVariable("username") String username, @PathVariable("password") String password)
	{
		Student student = studentService.login(username, password);
		
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
	
	@GetMapping("/findToAddInBatch/{course}")
	public ResponseEntity<List<Student>> findToAddInBatch(@PathVariable("course") String course)
	{
		List<Student> studentList = studentService.findStudentToAddInBatch(course);
		
		return ResponseEntity.status(HttpStatus.OK).body(studentList);
	}
	
	@PutMapping("/allotBatch/{id}")
	public ResponseEntity<Student> alltoBatch(@PathVariable("id") int id, @RequestBody Batch batch)
	{
		Student student = studentService.allotBatch(id, batch);
		
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
	
	@GetMapping("/studentcount")
	public ResponseEntity<Long> countStudent()
	{
		long count = studentService.CountStudent();
		
		return ResponseEntity.status(HttpStatus.OK).body(count);
	}
	
	@GetMapping("/countByStatus/{status}")
	public ResponseEntity<Long> countByStatus(@PathVariable("status") String status)
	{
		long count = studentService.CountByStatus(status);
		
		return ResponseEntity.status(HttpStatus.OK).body(count);
	}
	
}
