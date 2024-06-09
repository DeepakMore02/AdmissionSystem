package com.service;

import java.util.List;

import com.model.Education;

public interface EducationService
{
	public String addEducation(int id,List<Education> education);
	
	public Education saveEducation(int id, Education education);
	
}
