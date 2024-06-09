package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Course;
import com.model.Trainer;
import com.service.TrainerService;
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/trainer")
@RestController
public class TrainerController
{
	@Autowired
	TrainerService trainerService;
	
	@PostMapping("/save")
	public ResponseEntity<Trainer> save(@RequestBody Trainer trainer)
	{
		Trainer t = trainerService.save(trainer);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(t);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Trainer>> getAll()
	{
		List<Trainer> trainerList = trainerService.getAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(trainerList);
	}
	
	@GetMapping("/get/{name}")
	 public ResponseEntity<List<Trainer>> getTrainerNameByName(@PathVariable("name") String name)
	{
		String n = name.toLowerCase();
       List<Trainer> trainer = trainerService.getTrainerByName(n);
       return ResponseEntity.status(HttpStatus.OK).body(trainer);
     }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteTrainer(@PathVariable("id") int id)
	{
		Boolean b = trainerService.deleteTrainer(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(b);
	}
}
