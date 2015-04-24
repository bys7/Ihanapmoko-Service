package com.ihanapmoko.manager;

import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Advertisement;
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
			
			System.out.println("-- Query Result : " + hasAdded);
			System.out.println("-- Created ID   : " + advertisement.getId());
			
			String jsonAdvertisement = hasAdded ? serviceFactory.parseObject(advertisement) : "" ;
			
			json.put(ServiceMethodNames.CREATE_ADVERTISEMENT, jsonAdvertisement);
		}
		
		return json;
		
		
	}
	
}
