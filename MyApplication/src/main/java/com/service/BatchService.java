package com.service;

import java.util.List;

import com.model.Batch;

public interface BatchService
{
	public Batch save(Batch batch);
	
	public List<Batch> getAll();
	
	//public List<Batch> getByCourse(int courseId);
	public List<Batch> findBatchesByCourseName(String courseName);
	
	public Batch batchDetail(String name);
}	
