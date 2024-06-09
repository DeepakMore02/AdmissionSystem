package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EducationRepository;
import com.dao.StudentRepository;
import com.model.Education;
import com.model.Student;

@Service
public class EducationServiceImplementation implements EducationService
{
	@Autowired
	EducationRepository educationRepo;
	
	@Autowired
	StudentRepository studentRepo;
	
	@Override
	
	public String addEducation(int id,List<Education> education)
	{
		for(Education edu :education)
		{
			
		}
		return null;
	}

	@Override
	public Education saveEducation(int id, Education education)
	{
		Student student = studentRepo.findById(id).orElse(null);
		if(student != null)
		{
			education.setStudent(student);
			Education edu = educationRepo.save(education);
			System.out.println("inside education service");
			return edu;
		}
		return null;
	}

}
