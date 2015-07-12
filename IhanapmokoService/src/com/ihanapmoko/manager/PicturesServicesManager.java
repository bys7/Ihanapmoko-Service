package com.ihanapmoko.manager;

import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.PictureGallery;
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
		}else if(serviceMethod.equals(ServiceMethodNames.FETCH_PICTURE_GALLERY_BY_AD_ID)){
			String advertisementId 	= params[1].getValue();	
			
			List<PictureGallery> gallery = picturesServicesDao.fetchPictureByAdId(advertisementId);
			
			String jsonPicturesServices = serviceFactory.parseObject(gallery);
			
			json.put(ServiceMethodNames.FETCH_PICTURE_GALLERY_BY_AD_ID, jsonPicturesServices);
		}
		
		return json;
	}
	
}
