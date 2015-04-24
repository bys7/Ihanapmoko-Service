package com.ihanapmoko.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.ihanapmoko.bean.Location;
import com.ihanapmoko.dao.LocationDAO;
import com.ihanapmoko.utility.HibernateManager;

public class LocationDAOImpl extends GenericDAOImpl implements LocationDAO {

	public List<Location> getAllLocation(){
		
		List<Location> result;
		
		Session session   = null;
		Criteria criteria = null;
		
		try{
			
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(Location.class);
			
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
