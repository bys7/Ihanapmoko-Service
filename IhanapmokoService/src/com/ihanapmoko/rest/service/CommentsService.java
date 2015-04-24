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
import com.ihanapmoko.manager.CommentsManager;

@Path("/comments")
public class CommentsService {
	
	ServiceFactory serviceFactory 	= new ServiceFactory();

	
	@POST
	@Path("/createComments")
	@Consumes ("application/json")
	@Produces ("application/json")
	public ServiceResult createComments(String params){
		
		System.out.println(this.getClass().getName() + " START ~ createComments()");
		
		ServiceResult sr 			= new ServiceResult();
		String jsonComments 		= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);
			
			CommentsManager manager = new CommentsManager();
			JSONObject jsonResult  = manager.getJSONResponse(valuePairs);
			
			jsonComments = jsonResult.get(ServiceMethodNames.CREATE_COMMENT).toString();
			
			sr.setObj(jsonComments);
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");			
			
		}catch(Exception e){		
			sr.setObj(jsonComments);
			sr.setStatus(-1);
			sr.setDescription("System Error.");		
			
			e.printStackTrace();
		}
		
		
		return sr;
	}
	
}
