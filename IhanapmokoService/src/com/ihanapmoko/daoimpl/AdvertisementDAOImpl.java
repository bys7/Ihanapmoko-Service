package com.ihanapmoko.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.Dashboard;
import com.ihanapmoko.bean.SearchResult;
import com.ihanapmoko.bean.SearchSize;
import com.ihanapmoko.dao.AdvertisementDAO;
import com.ihanapmoko.utility.HibernateManager;

public class AdvertisementDAOImpl extends GenericDAOImpl implements AdvertisementDAO {

	public List<SearchResult> searchAdvertisment(String searchParameter, String locationId, String categoryId, String startRow){
		
		List<SearchResult> searchResultList 	= null;
		SearchResult searchResult				= null;
		List<Object[]> listResult 				= null;		
		Session session   						= null;
		
		String conditionFiller					= "";
		
		System.out.println("AdvertisementDAOImpl searchAdvertisment PARAM" + searchParameter);
		
		if(locationId!=null && !locationId.equals("")){
			conditionFiller = "AND a.location_id = " + locationId + " ";
		}
		if(categoryId!=null && !categoryId.equals("")){
			conditionFiller = conditionFiller + "AND a.category_id = " + categoryId + " ";
		}
		
		
		try{
			
			session = HibernateManager.getMySql();
			session.beginTransaction();
						
			SQLQuery sqlQuery = session.createSQLQuery(
					"SELECT a.id, a.advertisement, a.date_created, c.location, d.picture_destination " +
					"FROM advertisement a, category b, location c, pictures d " +
					"WHERE (a.advertisement LIKE '%" + searchParameter + "%' "+
					"OR a.description LIKE '%" + searchParameter + "%' " +
					"AND b.category LIKE '%" + searchParameter + "%') " + 					
					conditionFiller +					
					"AND a.location_id = c.id " +
					"AND a.picture_id = d.id " +
					"AND a.category_id = b.id " + 
					"ORDER BY a.id  DESC "
					+ "LIMIT " + startRow + ",12")
					
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
	
	public boolean create(Advertisement advertisement){
		boolean result = false;
		
		try{
			if(advertisement!=null){
				return save(advertisement);
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
	
	public Advertisement getAdvertisementById(int id){
		Advertisement advertisement = new Advertisement();
		
		Session session   	= null;
		Criteria criteria 	= null;
		List<Advertisement> list = null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(Advertisement.class)
					.add(Expression.eq("id", id));
			
			list = criteria.list();
			
			if(list!=null){
				advertisement = list.get(0);
			}
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return advertisement;
	}
	
	public Advertisement getAdvertisementByPictureId(int picture_id){
		Advertisement advertisement = new Advertisement();
		
		Session session   	= null;
		Criteria criteria 	= null;
		List<Advertisement> list = null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(Advertisement.class)
					.add(Expression.eq("picture_id", picture_id));
			
			list = criteria.list();
			
			if(list!=null){
				advertisement = list.get(0);
			}
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return advertisement;
	}
	
	public Dashboard fetchDashboardAdvertisementById(String id){
		
		Dashboard dashboardResult				= null;
		List<Object[]> listResult 				= null;		
		Session session   						= null;
		
		try{
			
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			SQLQuery sqlQuery = session.createSQLQuery(
					"SELECT a.id,"
					+ "a.advertisement,"
					+ "a.contact_number,"
					+ "b.category,"
					+ "a.email_address,"
					+ "a.date_created,"
					+ "c.location,"
					+ "a.item_condition,"
					+ "d.picture_destination,"
					+ "a.description,"
					+ "a.views, "
					+ "a.budget, "
					+ "a.number_is_private "
					+ "FROM advertisement a, "
						+ "category b,"
						+ "location c,"
						+ "pictures d "
					+ "WHERE a.id=" + id + " "
						+ "AND a.location_id = c.id "
						+ "AND a.picture_id = d.id "
						+ "AND a.category_id = b.id "					
					)
			
					.addScalar("ID", Hibernate.INTEGER)
					.addScalar("ADVERTISEMENT", Hibernate.STRING)
					.addScalar("CONTACT_NUMBER", Hibernate.STRING)
					.addScalar("CATEGORY", Hibernate.STRING)
					.addScalar("EMAIL_ADDRESS", Hibernate.STRING)
					.addScalar("DATE_CREATED", Hibernate.TIMESTAMP)
					.addScalar("LOCATION", Hibernate.STRING)
					.addScalar("ITEM_CONDITION", Hibernate.STRING)
					.addScalar("PICTURE_DESTINATION",Hibernate.STRING)
					.addScalar("DESCRIPTION", Hibernate.STRING)
					.addScalar("VIEWS", Hibernate.INTEGER)
					.addScalar("BUDGET", Hibernate.STRING)
					.addScalar("NUMBER_IS_PRIVATE", Hibernate.STRING);
					
			listResult = sqlQuery.list();
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			session.getTransaction();
			e.printStackTrace();
		}finally{
			if(dashboardResult==null){
				dashboardResult = new Dashboard();
				for(Object[] object : listResult){
					dashboardResult.setAdvertisement_id((Integer)object[0]);
					dashboardResult.setAdvertisement((String)object[1]);
					dashboardResult.setContact_number((String)object[2]);
					dashboardResult.setCategory((String)object[3]);
					dashboardResult.setEmail_address((String)object[4]);
					dashboardResult.setDate_created((Date)object[5]);
					dashboardResult.setLocation((String)object[6]);
					dashboardResult.setItem_condition((String)object[7]);
					dashboardResult.setPicture_destination((String)object[8]);
					dashboardResult.setDescription((String)object[9]);
					dashboardResult.setViews((Integer)object[10]);
					dashboardResult.setBudget((String)object[11]);
					dashboardResult.setNumber_is_private((String)object[12]);
				}
			}
			
			closeSession();
		}

		
		return dashboardResult;
	}
	
	public Advertisement fetchAdvertisementByParams(String name, String email_address, String contact_number, String password){
		Advertisement advertisement = new Advertisement();
		
		Session session   	= null;
		Criteria criteria 	= null;
		List<Advertisement> list = null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();
			
			criteria = session.createCriteria(Advertisement.class)
					.add(Expression.eq("advertisement", name))
					.add(Expression.eq("email_address", email_address))
					.add(Expression.eq("contact_number", contact_number))
					.add(Expression.eq("password", password));
			
			list = criteria.list();
			
			if(list!=null){
				advertisement = list.get(0);
			}
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return advertisement;
	}
	
	public boolean update(Advertisement advertisement){
		boolean result = false;
				
		try{
			if(advertisement!=null){
				result = edit(advertisement);
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
	
	public boolean delete(Advertisement advertisement){
		boolean result = false;
		
		try{
			if(advertisement!=null){
				result = remove(advertisement);
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
	
	public SearchSize searchAdvertismentSize(String searchParameter, String locationId, String categoryId){
		
		List<SearchResult> searchResultList 	= null;
		SearchResult searchResult				= null;
		List<Object[]> listResult 				= null;		
		Session session   						= null;
				
		SearchSize searchSize					= null;
		
		String conditionFiller					= "";
				
		int totalSize							= 0;
		
		System.out.println("AdvertisementDAOImpl searchAdvertisment PARAM" + searchParameter);
		
		if(locationId!=null && !locationId.equals("")){
			conditionFiller = "AND a.location_id = " + locationId + " ";
		}
		if(categoryId!=null && !categoryId.equals("")){
			conditionFiller = conditionFiller + "AND a.category_id = " + categoryId + " ";
		}
		
		
		try{
			
			session = HibernateManager.getMySql();
			session.beginTransaction();
						
			SQLQuery sqlQuery = session.createSQLQuery(
					"SELECT a.id, a.advertisement, a.date_created, c.location, d.picture_destination " +
					"FROM advertisement a, category b, location c, pictures d " +
					"WHERE (a.advertisement LIKE '%" + searchParameter + "%' "+
					"OR a.description LIKE '%" + searchParameter + "%' " +
					"AND b.category LIKE '%" + searchParameter + "%') " + 					
					conditionFiller +					
					"AND a.location_id = c.id " +
					"AND a.picture_id = d.id " +
					"AND a.category_id = b.id " + 
					"ORDER BY a.id  DESC ")
					
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
				searchSize = new SearchSize();
				
				totalSize = searchResultList.size();
				searchSize.setSearchTotalSize(totalSize);
			}
			
			closeSession();
		}
		
		return searchSize;		
	}
	
}
