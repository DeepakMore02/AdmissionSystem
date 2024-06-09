package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Batch;
import com.service.BatchService;
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/batch")
@RestController
public class BatchController
{
	@Autowired
	BatchService batchService;
	
	@PostMapping("/save")
	public ResponseEntity<Batch> save(@RequestBody Batch batch)
	{
		Batch b = batchService.save(batch);
		
		return ResponseEntity.status(HttpStatus.OK).body(b);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Batch>> getAll()
	{
		List<Batch> batchList = batchService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(batchList);
	}
	
	@GetMapping("/course/{courseName}")
    public ResponseEntity<List<Batch>> getBatchesByCourseName(@PathVariable String courseName) {
        List<Batch> batches = batchService.findBatchesByCourseName(courseName);
        if (batches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(batches, HttpStatus.OK);
    }
	
	@GetMapping("/batchdetail/{name}")
	public ResponseEntity<Batch> getBatchDetail(@PathVariable("name") String name)
	{
		Batch batch = batchService.batchDetail(name);
		return ResponseEntity.status(HttpStatus.OK).body(batch);
	}
}
