package com.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Student
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 private String name;
	 private String password;
	 private LocalDate date;
	 private String fatherName;
	 private String motherName;
	 private String permanentAddr;
	 private String presentAddr;
	 private String gender;
	 private LocalDate dob;
	 private String aadhar;
	 @Column(unique=true)
	 private String mobile;
	 private String parentMobile;
	 @Column(unique=true)
	 private String email;
	 private String parentEmail;
	 private String status;
	 private String course;
	
	 @OneToMany(fetch=FetchType.EAGER, orphanRemoval = true)
	 @JoinColumn(name = "student_id")
	 private List<Education> educationList;
	 
	 @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, orphanRemoval = true)
	 @JoinColumn(name = "student_id")
	 private List<Document> documentList;
	 
	 
	 @JsonIgnore
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "batch_id")
	 private Batch batch;
	 
	 public Student()
	 {
		 super();
	 }


	public Student(int id, String name, String password, LocalDate date, String fatherName, String motherName,
			String permanentAddr, String presentAddr, String gender, LocalDate dob, String aadhar, String mobile,
			String parentMobile, String email, String parentEmail, String status, String course,
			List<Education> educationList, List<Document> documentList, Batch batch)
	{
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.date = date;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.permanentAddr = permanentAddr;
		this.presentAddr = presentAddr;
		this.gender = gender;
		this.dob = dob;
		this.aadhar = aadhar;
		this.mobile = mobile;
		this.parentMobile = parentMobile;
		this.email = email;
		this.parentEmail = parentEmail;
		this.status = status;
		this.course = course;
		this.educationList = educationList;
		this.documentList = documentList;
		this.batch = batch;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getPermanentAddr() {
		return permanentAddr;
	}

	public void setPermanentAddr(String permanentAddr) {
		this.permanentAddr = permanentAddr;
	}

	public String getPresentAddr() {
		return presentAddr;
	}

	public void setPresentAddr(String presentAddr) {
		this.presentAddr = presentAddr;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public List<Education> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<Education> educationList) {
		this.educationList = educationList;
	}



	public List<Document> getDocumentList() {
		return documentList;
	}



	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}


	public Batch getBatch() {
		return batch;
	}


	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	 
	 
	 
}
