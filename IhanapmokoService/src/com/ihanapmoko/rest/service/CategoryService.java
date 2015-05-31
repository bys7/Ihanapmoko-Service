package com.ihanapmoko.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;
import com.ihanapmoko.helper.ServiceResult;
import com.ihanapmoko.manager.CategoryManager;

@Path("/category")
public class CategoryService {

	ServiceFactory serviceFactory 	= new ServiceFactory();
		
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchAllCategory")
	public ServiceResult fetchAllCategory(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchAllCategory()");
		
		ServiceResult sr 		= new ServiceResult();
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			CategoryManager manager = new CategoryManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			JSONArray jsonArray = new  JSONArray(jsonResult.get(ServiceMethodNames.FETCH_ALL_CATEGORY).toString());
			
			sr.setObj(jsonArray.toString());
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ fetchAllCategory()");
		
		return sr;
		
	}
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchCategoryById")
	public ServiceResult fetchCategoryById(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchCategoryById()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonCategory 	= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			CategoryManager manager = new CategoryManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonCategory = jsonResult.get(ServiceMethodNames.FETCH_CATEGORY_BY_ID).toString();
			
			sr.setObj(jsonCategory);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ fetchCategoryById()");
		
		return sr;
		
	}
}
