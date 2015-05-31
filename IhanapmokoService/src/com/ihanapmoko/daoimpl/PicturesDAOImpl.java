package com.ihanapmoko.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.ihanapmoko.bean.Pictures;
import com.ihanapmoko.bean.PicturesServices;
import com.ihanapmoko.dao.PicturesDAO;
import com.ihanapmoko.utility.HibernateManager;

public class PicturesDAOImpl extends GenericDAOImpl implements PicturesDAO{

	public Pictures getPictureById(int id){
		
		Pictures pictures = new Pictures();
		
		Session session   	= null;
		Criteria criteria 	= null;
		List<Pictures> list = null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(Pictures.class)
					.add(Expression.eq("id", id));
			
			list = criteria.list();
			
			if(list!=null){
				pictures = list.get(0);
			}
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return pictures;
		
	}
	
	public Pictures getPictureByName(String name){
		
		Pictures pictures = new Pictures();
		
		Session session   	= null;
		Criteria criteria 	= null;
		List<Pictures> list = null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(Pictures.class)
					.add(Expression.eq("picture_destination", name));
			
			list = criteria.list();
			
			if(list!=null){
				pictures = list.get(0);
			}
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return pictures;
		
	}
	
	public List<Pictures> fetchPicturesByAdvertisementId(int advertisementId){
		
		List<Pictures> pictureResultList 		= null;
		Pictures pictureResult					= null;
		List<Object[]> listResult 				= null;
		
		Session session   						= null;
		
		System.out.println("PicturesDAOImpl searchPicturesByAdvertisementId PARAM" + advertisementId);
		
		try{
			
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			SQLQuery sqlQuery = session.createSQLQuery(
					"SELECT c.id, b.picture_destination " +
					"FROM advertisement a, pictures b, pictures_services c " +
					"WHERE a.id = " + advertisementId + " " +
					"AND a.id = c.advertisement_id " +
					"AND c.pictures_id = b.id ")					
					
					
					.addScalar("ID", Hibernate.INTEGER)
					.addScalar("PICTURE_DESTINATION", Hibernate.STRING);
			
			listResult = sqlQuery.list();
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			session.getTransaction();
			e.printStackTrace();
		}finally{
			if(pictureResultList==null){
				pictureResultList = new ArrayList<Pictures>();
				
				for(Object[] object : listResult){
					pictureResult = new Pictures();
					pictureResult.setId((Integer)object[0]);
					pictureResult.setPicture_destination((String)object[1]);
					pictureResultList.add(pictureResult);
				}
			}
			
			closeSession();
		}
		
		return pictureResultList;		
	}
	
	public boolean create(Pictures pictures){
		boolean result = false;
		
		try{
			if(pictures!=null){				
				return save(pictures);
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return result;
	}
	
}
