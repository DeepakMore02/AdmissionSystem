package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminRepository;
import com.model.Admin;

@Service
public class AdminServiceImplementation implements AdminService{

	@Autowired
	AdminRepository adminRepo;
	
	public Admin addAdmin(Admin admin)
	{
		Admin a = adminRepo.save(admin);
		return a;
	}
	
	public boolean adminLogin(String adminUsername, String password)
	{
		Admin admin = adminRepo.findByAdminUsername(adminUsername);
		if(admin != null && admin.getPassword().equals(password))
		{
			return true;
		}
		return false;
	}
	
	public Admin getAdmin(String adminUsername)
	{
		Admin admin = adminRepo.findByAdminUsername(adminUsername);
		if(admin != null)
		{
			System.out.println(admin.getAdminUsername());
			return admin;
		}
		return null;
	}
}
