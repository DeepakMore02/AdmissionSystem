package com.controller;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.helper.FileUpload;
import com.model.Document;
import com.service.DocumentService;
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/doc")
@RestController
public class DocumentController
{
	@Autowired
	DocumentService documentService;
	

	 @PostMapping("/upload/{id}/{name}")
	 public ResponseEntity<Boolean> uploadFile(@PathVariable("id") int id, @PathVariable("name") String name,@RequestParam("file") MultipartFile file)
	 {
		 System.out.println(file.getOriginalFilename());
		 System.out.println(file.getSize());
		 System.out.println(file.getContentType());
		 Boolean output = false;
		 try
		 {
			boolean f = documentService.uploadFile(id,name,file);
			if(f)
			{
				output = true;
			}
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
	 }
	 
	 @GetMapping("/getOneDocument/{studentId}")
	 public ResponseEntity<ArrayList<Document>> getOneDocument(@PathVariable("studentId") int studentId)
	 {
		 ArrayList<Document> docs = (ArrayList<Document>) documentService.getOneDocument(studentId);
		 
		 return ResponseEntity.status(HttpStatus.OK).body(docs);
	 }
	 
	 @GetMapping("/getDocument/{id}")
	 public String getDocument(@PathVariable("id") int id)
	 {
		 String document = documentService.getDocument(id);
		 System.out.println(document);
		 
		 //return ResponseEntity.status(HttpStatus.OK).body(document);
		 return document;
	 }
	 
	 @DeleteMapping("/deleteDocument/{id}")
	 public ResponseEntity<String> deleteDocument(@PathVariable("id") int id)
	 {
		 String msg = documentService.deletedocument(id);
		 
		 return ResponseEntity.status(HttpStatus.OK).body(msg);
	 }
}
