package com.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.model.Document;

public interface DocumentService
{	
	public List<Document> getAll();
	
	boolean uploadFile(int id,String name,MultipartFile multipart);
	
	public List<Document> getOneDocument(int id);
	
	public String getDocument(int id);
	
	public String deletedocument(int id);
	
}
