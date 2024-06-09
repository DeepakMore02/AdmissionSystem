package com.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Education
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String qualification;
	private String college;
	private String stream;
	private LocalDate passingYear;
	private String grade;
	private double percentage;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private Student student;
	
	public Education()
	{
		super();
	}

	public Education(int id, String qualification, String college, String stream, LocalDate passingYear, String grade,
			double percentage, Student student) {
		super();
		this.id = id;
		this.qualification = qualification;
		this.college = college;
		this.stream = stream;
		this.passingYear = passingYear;
		this.grade = grade;
		this.percentage = percentage;
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public LocalDate getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(LocalDate passingYear) {
		this.passingYear = passingYear;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
	
}
