package com.service;

import com.model.Admin;

public interface AdminService {

	public Admin addAdmin(Admin admin);
	
	public boolean adminLogin(String adminUsername, String password);
	
	public Admin getAdmin(String adminUsername);
}
