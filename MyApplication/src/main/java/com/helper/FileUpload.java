package com.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload
{
	public final String UPLOAD_DIR = "C:\\Users\\DEEPAK\\SpringProjectTwo\\MyApplication\\src\\main\\resources\\static\\Docs";
	
	public boolean uploadFile(MultipartFile multipart)
	{
		boolean f = false;
		
		try
		{
			//USING NIO PACKAGE
			
			Files.copy(multipart.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipart.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			
			//USING IO PACKAGE
			/*
			InputStream is = multipart.getInputStream();
			byte data[] = new byte[is.available()];
			
			is.read(data);
			
			//write
			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+"\\"+multipart.getOriginalFilename());
			fos.write(data);
			fos.close();
			fos.flush();
			is.close();
			*/
			f = true;
			return f;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}
}
