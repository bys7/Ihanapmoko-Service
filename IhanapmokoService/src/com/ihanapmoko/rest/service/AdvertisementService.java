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
import com.ihanapmoko.manager.AdvertisementManager;
import com.ihanapmoko.manager.CategoryManager;


@Path("/advertisement")
public class AdvertisementService {

	ServiceFactory serviceFactory 	= new ServiceFactory();
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/searchAdvertisement")
	public ServiceResult searchAdvertisement(String params){
		
		System.out.println(this.getClass().getName() + " START ~ searchAdvertisement()");
		
		ServiceResult sr 			= new ServiceResult();
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			AdvertisementManager manager = new AdvertisementManager();
			JSONObject jsonResult  = manager.getJSONResponse(valuePairs);
			
			JSONArray jArray = new JSONArray(jsonResult.get(ServiceMethodNames.SEARCH_ADVERTISEMENT).toString());
			
			sr.setObj(jArray.toString());
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
			
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			
			e.printStackTrace();
		}
		System.out.println(this.getClass().getName() + " END ~ searchAdvertisement()");
		
		return sr;
	}
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/createAdvertisement")
	public ServiceResult createAdvertisement(String params){
		
		System.out.println(this.getClass().getName() + " START ~ createAdvertisement()");
		
		ServiceResult sr 			= new ServiceResult();
		String jsonAdvertisement	= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			AdvertisementManager manager = new AdvertisementManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
					
			jsonAdvertisement = jsonResult.get(ServiceMethodNames.CREATE_ADVERTISEMENT).toString();
			
			sr.setObj(jsonAdvertisement);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setObj(jsonAdvertisement);
			sr.setStatus(-1);
			sr.setDescription("System Error.");		
			
			e.printStackTrace();
		}
		
		
		return sr;
	}
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchAdvertisementById")
	public ServiceResult fetchAdvertisementById(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchAdvertisementById()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonCategory 	= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			AdvertisementManager manager = new AdvertisementManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonCategory = jsonResult.get(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_ID).toString();
			
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
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchAdvertisementByPictureId")
	public ServiceResult fetchAdvertisementByPictureId(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchAdvertisementById()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonCategory 	= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			AdvertisementManager manager = new AdvertisementManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonCategory = jsonResult.get(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PICTURE_ID).toString();
			
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
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchDashboardAdvertisementById")
	public ServiceResult fetchDashboardAdvertisementById(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchDashboardAdvertisementById()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonDashboard 	= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			AdvertisementManager manager = new AdvertisementManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonDashboard = jsonResult.get(ServiceMethodNames.FETCH_DASHBOARD_ADVERTISEMENT_BY_ID).toString();
			
			sr.setObj(jsonDashboard);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ fetchDashboardAdvertisementById()");
		
		return sr;
		
	}
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchAdvertisementByParams")
	public ServiceResult fetchAdvertisementByParams(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchAdvertisementById()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonAdvertisement 	= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			AdvertisementManager manager = new AdvertisementManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonAdvertisement = jsonResult.get(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PARAMS).toString();
			
			sr.setObj(jsonAdvertisement);
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
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/updateAdvertisment")
	public ServiceResult updateAdvertisement(String params){
		System.out.println(this.getClass().getName() + " START ~ updateAdvertisement()");
		
		ServiceResult sr 			= new ServiceResult();
		String jsonAdvertisement	= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			AdvertisementManager manager = new AdvertisementManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonAdvertisement = jsonResult.get(ServiceMethodNames.UPDATE_ADVERTISEMENT).toString();
			
			sr.setObj(jsonAdvertisement);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ updateAdvertisement()");
		
		return sr;
		
	}
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/deleteAdvertisment")
	public ServiceResult deleteAdvertisement(String params){
		System.out.println(this.getClass().getName() + " START ~ deleteAdvertisement()");
		
		ServiceResult sr 			= new ServiceResult();
		String jsonAdvertisement	= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			AdvertisementManager manager = new AdvertisementManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonAdvertisement = jsonResult.get(ServiceMethodNames.DELETE_ADVERTISEMENT).toString();
			
			sr.setObj(jsonAdvertisement);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ deleteAdvertisement()");
		
		return sr;
		
	}
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/searchAdvertisementSize")
	public ServiceResult searchAdvertisementSize(String params){
		
		System.out.println(this.getClass().getName() + " START ~ searchAdvertisement()");
		
		ServiceResult sr 			= new ServiceResult();
		String jsonSize				= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			AdvertisementManager manager = new AdvertisementManager();
			JSONObject jsonResult  = manager.getJSONResponse(valuePairs);
			
			jsonSize = jsonResult.get(ServiceMethodNames.SEARCH_ADVERTISEMENT_SIZE).toString();
						
			sr.setObj(jsonSize);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
			
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			
			e.printStackTrace();
		}
		System.out.println(this.getClass().getName() + " END ~ searchAdvertisement()");
		
		return sr;
	}
	
}
