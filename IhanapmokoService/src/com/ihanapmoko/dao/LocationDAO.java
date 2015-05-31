package com.ihanapmoko.dao;

import java.util.List;

import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.Location;

public interface LocationDAO {

	public List<Location> getAllLocation();
	public Location getLocationById(int id);
	
}
