package com.ihanapmoko.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.ihanapmoko.bean.Category;
import com.ihanapmoko.dao.CategoryDAO;
import com.ihanapmoko.utility.HibernateManager;

public class CategoryDAOImpl extends GenericDAOImpl implements CategoryDAO {

	public List<Category> getAllCategory(){
		
		List<Category> result;
		
		Session session   = null;
		Criteria criteria = null;
		
		try{
			
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(Category.class);
			
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
	
}
