package com.ihanapmoko.manager;

import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.SearchResult;
import com.ihanapmoko.dao.AdvertisementDAO;
import com.ihanapmoko.dao.DAOFactory;
import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;

public class AdvertisementManager {

	public JSONObject getJSONResponse(NameValuePair[] params)	throws JSONException {
		
		JSONObject json      				= new JSONObject();
		AdvertisementDAO advertisementDao  	= (AdvertisementDAO) DAOFactory.getDAO(Advertisement.class);
		ServiceFactory serviceFactory 		= new ServiceFactory();
		
		String serviceMethod 				= params[0].getValue();
		
		System.out.println("--- service method : " + serviceMethod );
		
		if(serviceMethod.equals(ServiceMethodNames.SEARCH_ADVERTISEMENT)){
			String searchParameter = params[1].getValue();
			
			List<SearchResult> search = advertisementDao.searchAdvertisment(searchParameter);
			
			String jsonSearch = serviceFactory.parseObject(search);
			
			json.put(ServiceMethodNames.SEARCH_ADVERTISEMENT, jsonSearch);
		}else if(serviceMethod.equals(ServiceMethodNames.CREATE_ADVERTISEMENT)){
			String jsonBean = params[1].getValue();
			
			Advertisement advertisement = (Advertisement) serviceFactory.getMapper(Advertisement.class, jsonBean);
			
			boolean hasAdded = advertisementDao.create(advertisement);
			
			String jsonAdvertisement = hasAdded ? serviceFactory.parseObject(advertisement) : "" ;
			
			json.put(ServiceMethodNames.CREATE_ADVERTISEMENT, jsonAdvertisement);
		}else if(serviceMethod.equals(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_ID)){
			
			int id = Integer.valueOf(params[1].getValue());
			
			Advertisement advertisement = advertisementDao.getAdvertisementById(id);
			
			String jsonClient = serviceFactory.parseObject(advertisement);
			
			json.put(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_ID, jsonClient);
			
		}else if(serviceMethod.equals(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PICTURE_ID)){
			
			int picture_id = Integer.valueOf(params[1].getValue());
			
			Advertisement advertisement = advertisementDao.getAdvertisementByPictureId(picture_id);
			
			String jsonClient = serviceFactory.parseObject(advertisement);
			
			json.put(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PICTURE_ID, jsonClient);
			
		}
		
		return json;
		
		
	}
	
}
