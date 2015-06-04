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
		UserDAO userDao  				= (UserDAOImpl) DAOFactory.getDAO(User.class);
		ServiceFactory serviceFactory 	= new ServiceFactory();
		
		String serviceMethod 			= params[0].getValue();
		
		System.out.println("--- service method : " + serviceMethod );
		
		if(serviceMethod.equals(ServiceMethodNames.GET_EMAIL_AND_PASSWORD)){
			String email_address	= params[1].getValue();
			String password 		= params[2].getValue();
			
			User user = userDao.getUserByEmailAndPassword(email_address, password);
			
			if (user!=null) {
				System.out.println("USER DATE CREATED:"
						+ user.getDate_created());
			}
			
			String jsonClient = serviceFactory.parseObject(user);
			
			json.put(ServiceMethodNames.GET_EMAIL_AND_PASSWORD, jsonClient);
			
		}
		
		 if(serviceMethod.equals(ServiceMethodNames.CREATE_USER)){
				
				String jsonBean = params[1].getValue();
				User bean = (User) serviceFactory.getMapper(User.class, jsonBean);
				boolean isCreated = userDao.create(bean);
				System.out.println("query result: " + isCreated);
				System.out.println("created id: " + bean.getId());
				String jsonClient = isCreated ? serviceFactory.parseObject(bean) : "";
				json.put(ServiceMethodNames.CREATE_USER, jsonClient);
			}
	        
	        if(serviceMethod.equals(ServiceMethodNames.GET_USER_BY_ID)){
	        	String id	= params[1].getValue();
				User user = userDao.getUserById(Integer.parseInt(id));
				String jsonClient = serviceFactory.parseObject(user);
				json.put(ServiceMethodNames.GET_USER_BY_ID, jsonClient);
	        }
	        
	        if(serviceMethod.equals(ServiceMethodNames.UPDATE_USER)){
				String jsonBean = params[1].getValue();
				User bean       = (User) serviceFactory.getMapper( User.class, jsonBean);
				boolean isCreated   = userDao.update(bean);
				System.out.println("-- Query Result : " + isCreated);
				System.out.println("-- Updated ID   : " + bean.getId());
				String jsonClient = serviceFactory.parseObject(bean);
				json.put(ServiceMethodNames.UPDATE_USER, jsonClient);
			}
		
		
		return json;
		
	}
	
}
