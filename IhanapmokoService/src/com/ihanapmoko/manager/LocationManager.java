package com.ihanapmoko.manager;

import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.Location;
import com.ihanapmoko.dao.DAOFactory;
import com.ihanapmoko.dao.LocationDAO;
import com.ihanapmoko.daoimpl.LocationDAOImpl;
import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;

public class LocationManager {

	public JSONObject getJSONResponse(NameValuePair[] params)	throws JSONException {
		
		JSONObject json      			= new JSONObject();
		LocationDAO locationDao  		= (LocationDAOImpl) DAOFactory.getDAO(Location.class);
		ServiceFactory serviceFactory 	= new ServiceFactory();
		
		String serviceMethod 			= params[0].getValue();
		
		System.out.println("--- service method : " + serviceMethod );
		
		if(serviceMethod.equals(ServiceMethodNames.FETCH_ALL_LOCATION)){
			
			List<Location> locationList = locationDao.getAllLocation();
						
			String jsonLocation = serviceFactory.parseObject(locationList);
			
			json.put(ServiceMethodNames.FETCH_ALL_LOCATION, jsonLocation);
			
		}else if(serviceMethod.equals(ServiceMethodNames.FETCH_LOCATION_BY_ID)){
			
			int id = Integer.valueOf(params[1].getValue());
			
			Location location = locationDao.getLocationById(id);
			
			String jsonLocation = serviceFactory.parseObject(location);
			
			json.put(ServiceMethodNames.FETCH_LOCATION_BY_ID, jsonLocation);
			
		}
		
		return json;
		
	}
	
}
