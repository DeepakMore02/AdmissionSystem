package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer>
{
	Trainer findByName(String name);
	
	List<Trainer> findByNameContainingIgnoreCase(String name);
}
