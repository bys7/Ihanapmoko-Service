package com.ihanapmoko.test;

import java.util.List;

import com.ihanapmoko.bean.User;
import com.ihanapmoko.dao.DAOFactory;
import com.ihanapmoko.dao.UserDAO;
import com.ihanapmoko.daoimpl.UserDAOImpl;

public class UserTesting {

	public static void main(String[] args){
		
		List<User> getAllUsers = null;
		
		UserDAO userDAO = (UserDAOImpl)DAOFactory.getDAO(User.class);
		
		
		getAllUsers = userDAO.getAllUsers();
		
		System.out.println("ALL USERS RESULT:" + getAllUsers);
		
		for(User userList : getAllUsers){
			System.out.println("USER ID: " + userList.getId());
		}
		
	}
	
}
