package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer>
{

	 @Query("SELECT b FROM Batch b WHERE b.course.cname = :courseName")
	 List<Batch> findBatchesByCourseName(@Param("courseName") String courseName);
	 
	 Batch findByName(String name);

}
