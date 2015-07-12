package com.ihanapmoko.dao;

import java.util.List;

import com.ihanapmoko.bean.PictureGallery;
import com.ihanapmoko.bean.PicturesServices;

public interface PicturesServicesDAO {

	public boolean create(PicturesServices picturesServices);
	public List<PictureGallery> fetchPictureByAdId(String id);
}
