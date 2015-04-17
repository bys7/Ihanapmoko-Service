package com.ihanapmoko.dao;

import java.util.List;

import com.ihanapmoko.bean.User;

public interface UserDAO {

	public List<User> getAllUsers();
	
	public User getUserByEmailAndPassword(String email_address, String password);
	
}
