package com.ihanapmoko.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.ihanapmoko.bean.SearchResult;
import com.ihanapmoko.dao.AdvertisementDAO;
import com.ihanapmoko.utility.HibernateManager;

public class AdvertisementDAOImpl extends GenericDAOImpl implements AdvertisementDAO {

	public List<SearchResult> searchAdvertisment(String searchParameter){
		
		List<SearchResult> searchResultList 	= null;
		SearchResult searchResult				= null;
		List<Object[]> listResult 				= null;
		
		Session session   						= null;
		
		System.out.println("AdvertisementDAOImpl searchAdvertisment PARAM" + searchParameter);
		
		try{
			
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			SQLQuery sqlQuery = session.createSQLQuery(
					"SELECT a.id, a.advertisement, a.date_created, c.location, d.picture_destination " +
					"FROM advertisement a, category b, location c, pictures d " +
					"WHERE a.advertisement LIKE '%" + searchParameter + "%' "+
					"OR a.description LIKE '%" + searchParameter + "%' " +
					"OR b.category LIKE '%" + searchParameter + "%' " +
					"AND a.location_id = c.id " +
					"AND a.picture_id = d.id ")
					
					.addScalar("ID", Hibernate.INTEGER)
					.addScalar("ADVERTISEMENT", Hibernate.STRING)
					.addScalar("DATE_CREATED", Hibernate.TIMESTAMP)
					.addScalar("LOCATION", Hibernate.STRING)
					.addScalar("PICTURE_DESTINATION", Hibernate.STRING);
			
			listResult = sqlQuery.list();
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			session.getTransaction();
			e.printStackTrace();
		}finally{
			if(searchResultList==null){
				searchResultList = new ArrayList<SearchResult>();
				
				for(Object[] object : listResult){
					searchResult = new SearchResult();
					searchResult.setAdvertisementId((Integer)object[0]);
					searchResult.setAdvertisement((String)object[1]);
					searchResult.setDate_created((Date)object[2]);
					searchResult.setLocation((String)object[3]);
					searchResult.setPicture_destination((String)object[4]);
					searchResultList.add(searchResult);
				}
			}
			
			closeSession();
		}
		
		return searchResultList;
		
	}
	
}
