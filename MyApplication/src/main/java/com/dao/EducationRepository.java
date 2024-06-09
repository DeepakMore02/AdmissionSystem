package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Education;
import com.model.Student;

public interface EducationRepository extends JpaRepository<Education, Integer>
{
	@Query("SELECT e.id FROM Education e WHERE e.student = :student")
    List<Integer> findStudentByEducationId(Student student);
}
