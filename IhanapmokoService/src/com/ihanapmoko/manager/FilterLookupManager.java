package com.ihanapmoko.manager;

import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.ihanapmoko.bean.FilterLookup;
import com.ihanapmoko.dao.DAOFactory;
import com.ihanapmoko.dao.FilterLookupDAO;
import com.ihanapmoko.daoimpl.FilterLookupDAOImpl;
import com.ihanapmoko.helper.ServiceFactory;
import com.ihanapmoko.helper.ServiceMethodNames;

public class FilterLookupManager {
	
public JSONObject getJSONResponse(NameValuePair[] params)	throws JSONException {
		
		JSONObject json      			= new JSONObject();
		FilterLookupDAO filterLookupDao = (FilterLookupDAOImpl) DAOFactory.getDAO(FilterLookup.class);
		ServiceFactory serviceFactory 	= new ServiceFactory();
		
		String serviceMethod 			= params[0].getValue();
		
		System.out.println("--- service method : " + serviceMethod );
		
		if(serviceMethod.equals(ServiceMethodNames.FETCH_ALL_FILTERLOOKUP)){
			
			List<FilterLookup> filterLookupList = filterLookupDao.getAllFilterLookup();
						
			String jsonClient = serviceFactory.parseObject(filterLookupList);
			
			json.put(ServiceMethodNames.FETCH_ALL_FILTERLOOKUP, jsonClient);
			
		}
		
		return json;
		
	}

}
