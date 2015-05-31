package com.ihanapmoko.dao;

import java.util.List;

import com.ihanapmoko.bean.Pictures;

public interface PicturesDAO {

	public Pictures getPictureById(int id);
	public Pictures getPictureByName(String name);
	public List<Pictures> fetchPicturesByAdvertisementId(int advertisementId);
	public boolean create(Pictures pictures);
	
}