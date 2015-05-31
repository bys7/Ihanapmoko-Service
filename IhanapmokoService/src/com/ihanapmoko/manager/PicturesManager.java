package com.ihanapmoko.manager;

import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Pictures;
import com.ihanapmoko.dao.DAOFactory;
import com.ihanapmoko.dao.PicturesDAO;
import com.ihanapmoko.daoimpl.PicturesDAOImpl;
import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;

public class PicturesManager {

	public JSONObject getJSONResponse(NameValuePair[] params)	throws JSONException {
		
		JSONObject json      			= new JSONObject();
		PicturesDAO picturesDao  		= (PicturesDAOImpl) DAOFactory.getDAO(Pictures.class);
		ServiceFactory serviceFactory 	= new ServiceFactory();
		
		String serviceMethod 			= params[0].getValue();
		
		System.out.println("--- service method : " + serviceMethod );
		
		if(serviceMethod.equals(ServiceMethodNames.FETCH_PICTURE_BY_ID)){
			
			int id = Integer.valueOf(params[1].getValue());
			
			Pictures picture = picturesDao.getPictureById(id);
			
			String jsonClient = serviceFactory.parseObject(picture);
			
			json.put(ServiceMethodNames.FETCH_PICTURE_BY_ID, jsonClient);
			
		}else if(serviceMethod.equals(ServiceMethodNames.FETCH_PICTURE_BY_NAME)){
			
			String name = params[1].getValue();
			
			Pictures picture = picturesDao.getPictureByName(name);
			
			String jsonClient = serviceFactory.parseObject(picture);
			
			json.put(ServiceMethodNames.FETCH_PICTURE_BY_NAME, jsonClient);
			
		}else if(serviceMethod.equals(ServiceMethodNames.FETCH_PICTURE_BY_AD_ID)){
			
			int advertisementId = Integer.valueOf(params[1].getValue());
			
			List<Pictures> picture = picturesDao.fetchPicturesByAdvertisementId(advertisementId);
			
			String jsonClient = serviceFactory.parseObject(picture);
			
			json.put(ServiceMethodNames.FETCH_PICTURE_BY_AD_ID, jsonClient);
			
		}else if(serviceMethod.equals(ServiceMethodNames.CREATE_PICTURES)){
			String jsonBean = params[1].getValue();
			
			Pictures pictures = (Pictures) serviceFactory.getMapper(Pictures.class, jsonBean);
			
			boolean hasAdded = picturesDao.create(pictures);
			
			String jsonPictures = hasAdded ? serviceFactory.parseObject(pictures) : "" ;
			
			json.put(ServiceMethodNames.CREATE_PICTURES, jsonPictures);
		}
		
		return json;
		
	}
	
}
