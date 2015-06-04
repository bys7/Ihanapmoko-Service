package com.ihanapmoko.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.ihanapmoko.bean.User;
import com.ihanapmoko.dao.UserDAO;
import com.ihanapmoko.utility.HibernateManager;

public class UserDAOImpl extends GenericDAOImpl implements UserDAO  {

	@Override
	public List<User> getAllUsers(){
		
		List<User> result;
		
		Session session   = null;
		Criteria criteria = null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(User.class);
			
			result = criteria.list();
			
			session.getTransaction().commit();
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return null;
		
	}
	
	@Override
	public User getUserByEmailAndPassword(String email_address, String password){
		
		User result			= null;
		
		Session session   	= null;
		Criteria criteria 	= null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(User.class)
					.add(Expression.eq("email_address",email_address))
					.add(Expression.eq("password",password));
			
			List<User> userList = criteria.list();
			System.out.println("USER LIST SIZE:" + userList.size());
			if(userList!=null && userList.size()>0){
				result = userList.get(0);
			}
			
			session.getTransaction().commit();			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return result;
		
	}
	
	@Override
	public boolean create(User user) {
		boolean result = false;

		try {
			if (user != null) {
				return save(user);
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}

		return result;
	}

	@Override
	public User getUserById(int id) {
		
        User result			= null;
		Session session   	= null;
		Criteria criteria 	= null;
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(User.class)
					.add(Expression.eq("id",id));
					
			result = (User) criteria.uniqueResult();
			
			session.getTransaction().commit();			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return result;
	}
	
	@Override
	public boolean update(User user) {
		boolean result = false;
		try{
			if(user!=null){
				result = edit(user);
			}else{
				result = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return result;
	}
	
}
