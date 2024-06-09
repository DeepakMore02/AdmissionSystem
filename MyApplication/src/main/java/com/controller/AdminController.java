package com.controller;

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

import com.model.Admin;
import com.service.AdminService;

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/admin")
@RestController
public class AdminController
{
	@Autowired
	AdminService adminService;
	
	@PostMapping("/add")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin)
	{
		Admin a = adminService.addAdmin(admin);
		
		return ResponseEntity.status(HttpStatus.OK).body(a);
	}
	
	@GetMapping("/adminLogin/{username}/{password}")
	public ResponseEntity<Boolean> AdminLogin(@PathVariable("username") String username, @PathVariable("password") String Password)
	{
		boolean b = adminService.adminLogin(username, Password);
		
		return ResponseEntity.status(HttpStatus.OK).body(b);
	}
	
	@GetMapping("/getAdmin/{adminUsername}")
	public ResponseEntity<Admin> getAdmin(@PathVariable("adminUsername") String adminUsername)
	{
		Admin admin = adminService.getAdmin(adminUsername);
		
		return ResponseEntity.status(HttpStatus.OK).body(admin);
	}
	
}
