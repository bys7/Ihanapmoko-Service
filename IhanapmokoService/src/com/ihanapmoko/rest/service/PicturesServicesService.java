package com.ihanapmoko.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;
import com.ihanapmoko.helper.ServiceResult;
import com.ihanapmoko.manager.PicturesServicesManager;

@Path("/picturesservices")
public class PicturesServicesService {

	ServiceFactory serviceFactory 	= new ServiceFactory();
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/createPicturesServices")
	public ServiceResult createPicturesServices(String params){
		
		System.out.println(this.getClass().getName() + " START ~ createPicturesServices()");
		
		ServiceResult sr 			= new ServiceResult();
		String jsonPicturesServices	= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			PicturesServicesManager manager = new PicturesServicesManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
					
			jsonPicturesServices = jsonResult.get(ServiceMethodNames.CREATE_PICTURES_SERVICES).toString();
			
			sr.setObj(jsonPicturesServices);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setObj(jsonPicturesServices);
			sr.setStatus(-1);
			sr.setDescription("System Error.");		
			
			e.printStackTrace();
		}
		
		
		return sr;
	}
	
}
