package com.ihanapmoko.manager;

import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.Dashboard;
import com.ihanapmoko.bean.SearchResult;
import com.ihanapmoko.bean.SearchSize;
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
			String searchParameter 	= params[1].getValue();
			String locationId		= params[2].getValue();
			String categoryId		= params[3].getValue();	
			String startRow			= params[4].getValue();
			
			List<SearchResult> search = advertisementDao.searchAdvertisment(searchParameter, locationId, categoryId, startRow);
			
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
			
			String jsonAdvertisement = serviceFactory.parseObject(advertisement);
			
			json.put(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_ID, jsonAdvertisement);
			
		}else if(serviceMethod.equals(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PICTURE_ID)){
			
			int picture_id = Integer.valueOf(params[1].getValue());
			
			Advertisement advertisement = advertisementDao.getAdvertisementByPictureId(picture_id);
			
			String jsonAdvertisement = serviceFactory.parseObject(advertisement);
			
			json.put(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PICTURE_ID, jsonAdvertisement);
			
		}else if(serviceMethod.equals(ServiceMethodNames.FETCH_DASHBOARD_ADVERTISEMENT_BY_ID)){
			
			String id = params[1].getValue();
			
			Dashboard dashboard = advertisementDao.fetchDashboardAdvertisementById(id);
			
			String jsonAdvertisement = serviceFactory.parseObject(dashboard);
			
			json.put(ServiceMethodNames.FETCH_DASHBOARD_ADVERTISEMENT_BY_ID, jsonAdvertisement);
			
		}
		else if(serviceMethod.equals(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PARAMS)){
			
			String name 			= params[1].getValue();
			String email_address 	= params[2].getValue();
			String contact_number	= params[3].getValue();
			String password			= params[4].getValue();
			
			Advertisement advertisement = advertisementDao.fetchAdvertisementByParams(name, email_address, contact_number, password);
			
			String jsonAdvertisement = serviceFactory.parseObject(advertisement);
			
			json.put(ServiceMethodNames.FETCH_ADVERTISEMENT_BY_PARAMS, jsonAdvertisement);
			
		}else if(serviceMethod.equals(ServiceMethodNames.UPDATE_ADVERTISEMENT)){
			
			String jsonBean = params[1].getValue();
			Advertisement advertisement = (Advertisement) serviceFactory.getMapper(Advertisement.class, jsonBean);
						
			boolean result = advertisementDao.update(advertisement);
			
			System.out.println("UPDATE ADVERTISEMENT RESULT:" + result);
			System.out.println("UPDATED ADVERTISEMENT ID:" + advertisement.getId());
			
			String jsonAdvertisement = serviceFactory.parseObject(advertisement);
			
			
			json.put(ServiceMethodNames.UPDATE_ADVERTISEMENT, jsonAdvertisement);
			
		}else if(serviceMethod.equals(ServiceMethodNames.DELETE_ADVERTISEMENT)){
			
			String jsonBean = params[1].getValue();
			Advertisement advertisement = (Advertisement) serviceFactory.getMapper(Advertisement.class, jsonBean);
						
			boolean result = advertisementDao.delete(advertisement);
			
			System.out.println("UPDATE ADVERTISEMENT RESULT:" + result);
			System.out.println("UPDATED ADVERTISEMENT ID:" + advertisement.getId());
			
			String jsonAdvertisement = serviceFactory.parseObject(advertisement);
			
			
			json.put(ServiceMethodNames.DELETE_ADVERTISEMENT, jsonAdvertisement);
			
		}else if(serviceMethod.equals(ServiceMethodNames.SEARCH_ADVERTISEMENT_SIZE)){
			String searchParameter 	= params[1].getValue();
			String locationId		= params[2].getValue();
			String categoryId		= params[3].getValue();	
			
			SearchSize searchSize = advertisementDao.searchAdvertismentSize(searchParameter, locationId, categoryId);
			
			String jsonSearch = serviceFactory.parseObject(searchSize);
			
			json.put(ServiceMethodNames.SEARCH_ADVERTISEMENT_SIZE, jsonSearch);
		}
		
		return json;		
		
	}
	
}
