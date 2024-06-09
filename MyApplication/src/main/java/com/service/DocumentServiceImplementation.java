package com.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dao.DocumentRepository;
import com.dao.StudentRepository;
import com.model.Document;
import com.model.Student;

@Service
public class DocumentServiceImplementation implements DocumentService
{
	@Autowired
	DocumentRepository documentRepo;
	
	@Autowired
	StudentRepository studentRepo;

	@Override
	public List<Document> getAll()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public final String UPLOAD_DIR1 ="C:\\Users\\DEEPAK\\Desktop\\Angular\\admissionsystem\\src\\assets\\Images";
	public final String UPLOAD_DIR2 ="C:\\Users\\DEEPAK\\Desktop\\Angular\\admissionsystem\\src\\assets\\Images";

	//public final String UPLOAD_DIR = "C:\\Users\\DEEPAK\\SpringProjectTwo\\MyApplication\\src\\main\\resources\\static\\Docs";
	
	public boolean uploadFile(int id,String name,MultipartFile multipart)
	{
		boolean f = false;
		int docId = 0;
		Student student = studentRepo.findById(id).orElse(null);
		//String username = student.getName();
		System.out.println(student.getDocumentList());
		for(Document d : student.getDocumentList())
		{
				if(d.getName().equals(name))
				{
					docId = d.getId();
					try
					{
						Path filePath = Paths.get(d.getLocation());
						boolean deleted = Files.deleteIfExists(filePath);
			            if (deleted) {
			                System.out.println("File deleted successfully.");
			            } else {
			                System.out.println("File does not exist or could not be deleted.");
			            }
			            
						InputStream is = multipart.getInputStream();
						byte data[] = new byte[is.available()];
						is.read(data);
						
						//write
						String path = UPLOAD_DIR1+"\\"+student.getName();
						FileOutputStream fos = new FileOutputStream(path+File.separator+multipart.getOriginalFilename());
						
						fos.write(data);
						fos.close();
						fos.flush();
						
//						String path2 = UPLOAD_DIR2+"\\"+student.getName();
//						FileOutputStream fos2 = new FileOutputStream(path2+File.separator+multipart.getOriginalFilename());
//						fos2.write(data);
//						fos2.close();
//						fos2.flush();
//						is.close();
						
						Document doc = documentRepo.findById(docId);
						doc.setName(name);
						doc.setLocation(path+"\\"+multipart.getOriginalFilename());
						doc.setFilename(multipart.getOriginalFilename());
						doc.setStudent(student);
						
						return true;
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
					
		}
		try
		{
			//USING NIO PACKAGE
			
			//Files.copy(multipart.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipart.getOriginalFilename())=, StandardCopyOption.REPLACE_EXISTING);
			
			//USING IO PACKAGE
			
			InputStream is = multipart.getInputStream();
			byte data[] = new byte[is.available()];
			
			is.read(data);
			
			String path = UPLOAD_DIR1+"\\"+student.getName();
			File file = new File(path);
			if(!file.exists())
			{
				file.mkdir();
			}
			String path2 = UPLOAD_DIR2+"\\"+student.getName();
			File file2 = new File(path2);
			if(!file2.exists())
			{
				file2.mkdir();
			}
			
			//Files.copy(multipart.getInputStream(),Paths.get(path));
			//write
			//FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+multipart.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(path+File.separator+multipart.getOriginalFilename());
			fos.write(data);
			fos.close();
			fos.flush();
			
			
//			FileOutputStream fos2 = new FileOutputStream(path2+File.separator+multipart.getOriginalFilename());
//			fos2.write(data);
//			fos2.close();
//			fos2.flush();
//			is.close();
		

			
			Document d = new Document();
			d.setName(name);
			d.setLocation(path+"\\"+multipart.getOriginalFilename());
			d.setFilename(multipart.getOriginalFilename());
			d.setStudent(student);
			
			documentRepo.save(d);
			
			f = true;
			return f;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}

	@Override
	public List<Document> getOneDocument(int studentId)
	{
		List<Document> docs = documentRepo.findDocumentsByStudentId(studentId);
		
		return docs;
	}

	public String getDocument(int id)
	{
		Document doc = documentRepo.findById(id);
		String path = doc.getLocation();
		System.out.println(path);
		try
		{
			
			//byte[] document = Files.readAllBytes(new File(path).toPath());
			
			FileInputStream fileInputStream = new FileInputStream(path);
            byte[] byteArray = new byte[fileInputStream.available()];
            fileInputStream.read(byteArray);
            fileInputStream.close();
            
			System.out.println(byteArray);
			return path;
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	public String deletedocument(int id)
	{
		Document d = documentRepo.findById(id);
		if(d != null)
		{
			documentRepo.delete(d);;
			System.out.println("DELETED");
			return "Deleted Successfully";
		}
		System.out.println("DELETION FAILED");
		return "Deletion Failed";
	}
}
