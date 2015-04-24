package com.ihanapmoko.dao;

import java.util.List;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.SearchResult;

public interface AdvertisementDAO {

	public List<SearchResult> searchAdvertisment(String searchParameter);
	
	public boolean create(Advertisement advertisement);
}
