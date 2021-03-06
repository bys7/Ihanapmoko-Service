package com.ihanapmoko.manager;

import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Category;
import com.ihanapmoko.dao.CategoryDAO;
import com.ihanapmoko.dao.DAOFactory;
import com.ihanapmoko.daoimpl.CategoryDAOImpl;
import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;

public class CategoryManager {

	public JSONObject getJSONResponse(NameValuePair[] params)	throws JSONException {
		
		JSONObject json      			= new JSONObject();
		CategoryDAO categoryDao  		= (CategoryDAOImpl) DAOFactory.getDAO(Category.class);
		ServiceFactory serviceFactory 	= new ServiceFactory();
		
		String serviceMethod 			= params[0].getValue();
		
		System.out.println("--- service method : " + serviceMethod );
		
		if(serviceMethod.equals(ServiceMethodNames.FETCH_ALL_CATEGORY)){
			
			List<Category> categoryList = categoryDao.getAllCategory();
						
			String jsonCategory = serviceFactory.parseObject(categoryList);
			
			json.put(ServiceMethodNames.FETCH_ALL_CATEGORY, jsonCategory);
			
		}else if(serviceMethod.equals(ServiceMethodNames.FETCH_CATEGORY_BY_ID)){
			
			int id = Integer.valueOf(params[1].getValue());
			
			Category category = categoryDao.getCategoryById(id);
			
			String jsonCategory = serviceFactory.parseObject(category);
			
			json.put(ServiceMethodNames.FETCH_CATEGORY_BY_ID, jsonCategory);
			
		}
		
		return json;
		
	}
	
	
}
