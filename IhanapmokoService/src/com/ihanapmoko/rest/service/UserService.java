package com.ihanapmoko.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;
import com.ihanapmoko.helper.ServiceResult;
import com.ihanapmoko.manager.UserManager;

@Path("/user")
public class UserService {

	ServiceFactory serviceFactory 	= new ServiceFactory();
	
	
	@POST
	@Consumes ("application/json")
	@Produces ("application/json")
	@Path("/getEmailAndPassword")
	public ServiceResult getUserViaEmailAndPassword(String params){
		System.out.println(this.getClass().getName() + " START ~ getUserViaEmailAndPassword()");
		
		ServiceResult sr 		= new ServiceResult();
		String jsonUser 		= null;
		
		try{
			NameValuePair[] valuePairs = (NameValuePair[]) serviceFactory.getMapper( NameValuePair[].class, params);

			UserManager manager = new UserManager();
			JSONObject jsonResult = manager.getJSONResponse(valuePairs);
			
//			JSONArray jArray = new JSONArray(jsonResult.get(ServiceMethodNames.GET_EMAIL_AND_PASSWORD).toString());
			jsonUser = jsonResult.get(ServiceMethodNames.GET_EMAIL_AND_PASSWORD).toString();
			
			sr.setObj(jsonUser.toString());
			sr.setStatus(0);
			sr.setDescription("SR Processed Successfully.");
		}catch(Exception e){			
			sr.setStatus(-1);
			sr.setDescription("System Error.");
			sr.setObj(null);
			e.printStackTrace();
		}
		
		System.out.println(this.getClass().getName() + " END ~ getUserViaEmailAndPassword()");
		
		return sr;
		
		
	}
	
}
