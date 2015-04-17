package com.ihanapmoko.manager;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.User;
import com.ihanapmoko.dao.DAOFactory;
import com.ihanapmoko.dao.UserDAO;
import com.ihanapmoko.daoimpl.UserDAOImpl;
import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;

public class UserManager {

	public JSONObject getJSONResponse(NameValuePair[] params)	throws JSONException {
		
		JSONObject json      			= new JSONObject();
		UserDAO userDao  			= (UserDAOImpl) DAOFactory.getDAO(User.class);
		ServiceFactory serviceFactory 	= new ServiceFactory();
		
		String serviceMethod 			= params[0].getValue();
		
		System.out.println("--- service method : " + serviceMethod );
		
		if(serviceMethod.equals(ServiceMethodNames.GET_EMAIL_AND_PASSWORD)){
			String email_address	= params[1].getValue();
			String password 		= params[2].getValue();
			
			User user = userDao.getUserByEmailAndPassword(email_address, password);
			
			System.out.println("USER DATE CREATED:" + user.getDate_created());
			
			String jsonClient = serviceFactory.parseObject(user);
			
			json.put(ServiceMethodNames.GET_EMAIL_AND_PASSWORD, jsonClient);
			
		}
		
		
		return json;
		
	}
	
}
