package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.model.Batch;
import com.model.Student;

public interface StudentService
{
	public Map<Boolean,String> registerStudent(Student student);
	
	public Object addEnquiry(Student student);
	
	public ArrayList<Student> getAllEnquiry();
	
	public Student getById(int id);
	
	public ArrayList<Student> getByName(String name);
	
	public Student updateStudent(Student student);
	
	public Map<Boolean,String> deleteStudent(int id);
	
	public List<Student> sortByStatus(String status);
	
	public Student login(String username, String password);
	
	public ArrayList<Student> findStudentToAddInBatch(String course);
	
	public Student allotBatch(int id, Batch batch);
	
	public long CountStudent();
	
	public long CountByStatus(String status);
	
}
