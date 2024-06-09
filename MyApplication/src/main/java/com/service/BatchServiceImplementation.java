package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BatchRepository;
import com.model.Batch;

@Service
public class BatchServiceImplementation implements BatchService
{
	@Autowired
	BatchRepository batchRepo;
	
	@Override
	public Batch save(Batch batch)
	{
		Batch b = batchRepo.save(batch);
		return b;
	}

	@Override
	public List<Batch> getAll()
	{
		List<Batch> batchList = batchRepo.findAll();
		return batchList;
	}

	public List<Batch> findBatchesByCourseName(String courseName) {
        return batchRepo.findBatchesByCourseName(courseName);
    }

	@Override
	public Batch batchDetail(String name)
	{
		Batch batch = batchRepo.findByName(name);
		return batch;
	}

}
