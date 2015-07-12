package com.ihanapmoko.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.ihanapmoko.bean.PictureGallery;
import com.ihanapmoko.bean.PicturesServices;
import com.ihanapmoko.dao.PicturesServicesDAO;
import com.ihanapmoko.utility.HibernateManager;

public class PicturesServicesDAOImpl extends GenericDAOImpl implements PicturesServicesDAO{

	public boolean create(PicturesServices picturesServices){
		boolean result = false;
		
		try{
			if(picturesServices!=null){				
				return save(picturesServices);
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
	
	public List<PictureGallery> fetchPictureByAdId(String id){
		
		List<PictureGallery> pictureResultList 	= null;
		PictureGallery pictureGalleryResult		= null;
		List<Object[]> listResult 				= null;		
		Session session   						= null;
		
		try{
			
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			SQLQuery sqlQuery = session.createSQLQuery(
					"SELECT a.id,"
					+ "b.picture_destination "					
					+ "FROM pictures_services a, "
						+ "pictures b "
					+ "WHERE a.advertisement_id = " + id + " "
						+ "AND a.pictures_id = b.id "					
					)
			
					.addScalar("ID", Hibernate.INTEGER)
					.addScalar("PICTURE_DESTINATION",Hibernate.STRING);
					
			listResult = sqlQuery.list();
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			session.getTransaction();
			e.printStackTrace();
		}finally{
			if(pictureGalleryResult==null){
				pictureResultList = new ArrayList<PictureGallery>();				
				for(Object[] object : listResult){
					pictureGalleryResult = new PictureGallery();
					pictureGalleryResult.setId((Integer)object[0]);
					pictureGalleryResult.setPicture_destination((String)object[1]);
					pictureResultList.add(pictureGalleryResult);					
				}
			}
			
			closeSession();
		}
		
		return pictureResultList;
	}
	
}
