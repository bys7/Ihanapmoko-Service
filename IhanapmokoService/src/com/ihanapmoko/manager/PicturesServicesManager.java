package com.ihanapmoko.manager;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.PicturesServices;
import com.ihanapmoko.dao.DAOFactory;
import com.ihanapmoko.dao.PicturesServicesDAO;
import com.ihanapmoko.daoimpl.PicturesServicesDAOImpl;
import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;

public class PicturesServicesManager {

	public JSONObject getJSONResponse(NameValuePair[] params)	throws JSONException {
		
		JSONObject json      						= new JSONObject();
		PicturesServicesDAO picturesServicesDao 	= (PicturesServicesDAOImpl) DAOFactory.getDAO(PicturesServices.class);
		ServiceFactory serviceFactory 				= new ServiceFactory();
		
		String serviceMethod 			= params[0].getValue();
		
		System.out.println("--- service method : " + serviceMethod );
		
		if(serviceMethod.equals(ServiceMethodNames.CREATE_PICTURES_SERVICES)){
			String jsonBean = params[1].getValue();
			
			PicturesServices picturesServices = (PicturesServices) serviceFactory.getMapper(PicturesServices.class, jsonBean);
			
			boolean hasAdded = picturesServicesDao.create(picturesServices);
			
			String jsonPicturesServices = hasAdded ? serviceFactory.parseObject(picturesServices) : "" ;
			
			json.put(ServiceMethodNames.CREATE_PICTURES_SERVICES, jsonPicturesServices);
		}
		
		return json;
	}
	
}
