package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>
{
	public Admin findByAdminUsername(String adminUsername);
}
