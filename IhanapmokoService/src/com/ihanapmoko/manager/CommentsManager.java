package com.ihanapmoko.manager;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Comments;
import com.ihanapmoko.dao.CommentsDAO;
import com.ihanapmoko.dao.DAOFactory;
import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;

public class CommentsManager {

	public JSONObject getJSONResponse(NameValuePair[] params)	throws JSONException {
		
		JSONObject json      				= new JSONObject();
		CommentsDAO commentsDao  			= (CommentsDAO) DAOFactory.getDAO(Comments.class);
		ServiceFactory serviceFactory 		= new ServiceFactory();
		
		String serviceMethod 			= params[0].getValue();
		
		System.out.println("--- service method : " + serviceMethod );
		
		if(serviceMethod.equals(ServiceMethodNames.CREATE_COMMENT)){
			
			System.out.println("CREATE COMMENTS");
			
			String jsonBean  = params[1].getValue();
			
			Comments comments = (Comments) serviceFactory.getMapper(Comments.class, jsonBean);
						
			boolean hasAdded = commentsDao.create(comments);
			
			System.out.println("-- Query Result : " + hasAdded);
			System.out.println("-- Created ID   : " + comments.getId());
			
			String jsonComments = hasAdded ? serviceFactory.parseObject(comments) : "";
			
			json.put(ServiceMethodNames.CREATE_COMMENT, jsonComments);
			
		}
		
		return json;
	}
	
}
