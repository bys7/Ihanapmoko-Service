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
import com.ihanapmoko.manager.AdvertisementManager;
import com.ihanapmoko.manager.PicturesManager;

@Path("/pictures")
public class PicturesService {

	ServiceFactory serviceFactory 	= new ServiceFactory();
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchPictureById")
	public ServiceResult fetchPictureById(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchPictureById()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonPicture		= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			PicturesManager manager = new PicturesManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonPicture = jsonResult.get(ServiceMethodNames.FETCH_PICTURE_BY_ID).toString();
			
			sr.setObj(jsonPicture);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ fetchPictureById()");
		
		return sr;
	}	
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchPictureByName")
	public ServiceResult fetchPictureByName(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchPictureByName()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonPicture		= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			PicturesManager manager = new PicturesManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonPicture = jsonResult.get(ServiceMethodNames.FETCH_PICTURE_BY_NAME).toString();
			
			sr.setObj(jsonPicture);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ fetchPictureByName()");
		
		return sr;
	}
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/fetchPictureByAdId")
	public ServiceResult fetchPictureByAdvertisementId(String params){
		System.out.println(this.getClass().getName() + " START ~ fetchPictureByAdvertisementId()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonPicture		= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			PicturesManager manager = new PicturesManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
			jsonPicture = jsonResult.get(ServiceMethodNames.FETCH_PICTURE_BY_AD_ID).toString();
			
			sr.setObj(jsonPicture);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ fetchPictureByAdvertisementId()");
		
		return sr;
	}
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/createPictures")
	public ServiceResult createPictures(String params){
		
		System.out.println(this.getClass().getName() + " START ~ createPictures()");
		
		ServiceResult sr 			= new ServiceResult();
		String jsonPictures			= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			PicturesManager manager = new PicturesManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
					
			jsonPictures = jsonResult.get(ServiceMethodNames.CREATE_PICTURES).toString();
			
			sr.setObj(jsonPictures);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){
			sr.setObj(jsonPictures);
			sr.setStatus(-1);
			sr.setDescription("System Error.");		
			
			e.printStackTrace();
		}
		
		
		return sr;
	}
}
