package com.ihanapmoko.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

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
	
	public Category getCategoryById(int id){
		Category category = new Category();
		
		Session session   	= null;
		Criteria criteria 	= null;
		List<Category> list = null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(Category.class)
					.add(Expression.eq("id", id));
			
			list = criteria.list();
			
			if(list!=null){
				category = list.get(0);
			}
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return category;
	}
	
	
}
