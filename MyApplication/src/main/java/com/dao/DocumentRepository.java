package com.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>
{
	 @Query("SELECT d FROM Document d JOIN FETCH d.student s WHERE s.id = :studentId")
	 List<Document> findDocumentsByStudentId(int studentId);
	 
	 Document findById(int id);
}
