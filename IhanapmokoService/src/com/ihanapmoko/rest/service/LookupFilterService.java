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
import com.ihanapmoko.manager.FilterLookupManager;


@Path("/filterlookup")
public class LookupFilterService {
	
	ServiceFactory serviceFactory 	= new ServiceFactory();
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchFilterLookup")
	public ServiceResult fetchAllFilterLookup(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchAllFilterLookup()");
		
		ServiceResult sr 		= new ServiceResult();
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			FilterLookupManager manager = new FilterLookupManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			JSONArray jsonArray = new  JSONArray(jsonResult.get(ServiceMethodNames.FETCH_ALL_FILTERLOOKUP).toString());
			
			sr.setObj(jsonArray.toString());
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ fetchAllFilterLookup()");
		
		return sr;
	}
	
}
