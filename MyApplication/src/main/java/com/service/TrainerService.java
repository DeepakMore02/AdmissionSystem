package com.service;

import java.util.List;

import com.model.Trainer;

public interface TrainerService
{
	public Trainer save(Trainer trainer);
	
	public List<Trainer> getAll();
	
	public List<Trainer> getTrainerByName(String name);
	
	public boolean deleteTrainer(int id);
}
