package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TrainerRepository;
import com.model.Trainer;

@Service
public class TrainerServiceImplementation implements TrainerService
{
	@Autowired
	TrainerRepository trainerRepo;

	@Override
	public Trainer save(Trainer trainer)
	{
		Trainer t = trainerRepo.save(trainer);
		return t;
	}

	@Override
	public List<Trainer> getAll()
	{
		List<Trainer> t = trainerRepo.findAll();
		return t;
	}

	@Override
	public List<Trainer> getTrainerByName(String name)
	{
		return trainerRepo.findByNameContainingIgnoreCase(name);
	}

	public boolean deleteTrainer(int id)
	{
		Trainer trainer = trainerRepo.findById(id).orElse(null);
		if(trainer != null)
		{
			trainerRepo.delete(trainer);
			return true;
		}
		
		return false;
	}
}
