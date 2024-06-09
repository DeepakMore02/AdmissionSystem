package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.EducationRepository;
import com.dao.StudentRepository;
import com.model.Education;
import com.model.Student;
import com.service.EducationService;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/education")
@RestController
public class EducationController
{
	@Autowired
	EducationService eduService;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	EducationRepository educationRepo;
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEducation(@PathVariable("id") int id)
	{
		String msg = "deletion Failed";
		try
		{
			educationRepo.deleteById(id);
			msg = "DELETED";
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@PostMapping("/saveEducation/{id}")
	public ResponseEntity<Education> saveEducation(@PathVariable("id") int id, @RequestBody Education education)
	{
		Education e = eduService.saveEducation(id, education);
		System.out.println(e.getCollege());
		System.out.println("Inside Save Education");
		return ResponseEntity.status(HttpStatus.OK).body(e);
	}
	
}
