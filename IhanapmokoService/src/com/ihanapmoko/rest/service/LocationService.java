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
import com.ihanapmoko.manager.LocationManager;

@Path("/location")
public class LocationService {

	ServiceFactory serviceFactory 	= new ServiceFactory();
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchAllLocation")
	public ServiceResult fetchAllLocation(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchAllLocation()");
		
		ServiceResult sr 		= new ServiceResult();
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			LocationManager manager = new LocationManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			JSONArray jsonArray = new  JSONArray(jsonResult.get(ServiceMethodNames.FETCH_ALL_LOCATION).toString());
			
			sr.setObj(jsonArray.toString());
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ fetchAllLocation()");
		
		return sr;
	}
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchLocationById")
	public ServiceResult fetchLocationById(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchLocationById()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonLocation		= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			LocationManager manager = new LocationManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonLocation = jsonResult.get(ServiceMethodNames.FETCH_LOCATION_BY_ID).toString();
			
			sr.setObj(jsonLocation);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ fetchLocationById()");
		
		return sr;
	}	
}
