package com.jwt.app.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.app.Entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);
	User findByName(String name);
	
	
}
