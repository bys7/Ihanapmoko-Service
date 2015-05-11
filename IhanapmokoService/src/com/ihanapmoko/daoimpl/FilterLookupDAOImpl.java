package com.ihanapmoko.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.ihanapmoko.bean.FilterLookup;
import com.ihanapmoko.dao.FilterLookupDAO;
import com.ihanapmoko.utility.HibernateManager;

public class FilterLookupDAOImpl extends GenericDAOImpl implements FilterLookupDAO {

	public List<FilterLookup> getAllFilterLookup(){
		
		List<FilterLookup> result;
		
		Session session = null;
		Criteria criteria = null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(FilterLookup.class);
			
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
