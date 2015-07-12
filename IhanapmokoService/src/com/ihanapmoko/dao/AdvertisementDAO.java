package com.ihanapmoko.dao;

import java.util.List;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.Dashboard;
import com.ihanapmoko.bean.SearchResult;
import com.ihanapmoko.bean.SearchSize;

public interface AdvertisementDAO {

	public List<SearchResult> searchAdvertisment(String searchParameter, String locationId, String categoryId, String startRow);	
	public boolean create(Advertisement advertisement);
	public Advertisement getAdvertisementById(int id);
	public Advertisement getAdvertisementByPictureId(int picture_id);
	public Dashboard fetchDashboardAdvertisementById(String id);
	public Advertisement fetchAdvertisementByParams(String name, String email_address, String contact_number, String password);
	public boolean update(Advertisement advertisement);
	public boolean delete(Advertisement advertisement);
	public SearchSize searchAdvertismentSize(String searchParameter, String locationId, String categoryId);
}
