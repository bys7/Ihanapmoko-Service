package com.ihanapmoko.dao;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.Bidding;
import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.Comments;
import com.ihanapmoko.bean.FilterLookup;
import com.ihanapmoko.bean.Location;
import com.ihanapmoko.bean.Pictures;
import com.ihanapmoko.bean.PicturesServices;
import com.ihanapmoko.bean.User;
import com.ihanapmoko.daoimpl.AdvertisementDAOImpl;
import com.ihanapmoko.daoimpl.AdvertisementServicesDAOImpl;
import com.ihanapmoko.daoimpl.BiddingDAOImpl;
import com.ihanapmoko.daoimpl.CategoryDAOImpl;
import com.ihanapmoko.daoimpl.CommentsDAOImpl;
import com.ihanapmoko.daoimpl.FilterLookupDAOImpl;
import com.ihanapmoko.daoimpl.GenericDAOImpl;
import com.ihanapmoko.daoimpl.LocationDAOImpl;
import com.ihanapmoko.daoimpl.PicturesDAOImpl;
import com.ihanapmoko.daoimpl.PicturesServicesDAOImpl;
import com.ihanapmoko.daoimpl.UserDAOImpl;

public final class DAOFactory {

	private DAOFactory(){  }
	
	public static GenericDAOImpl getDAO(Class<?> c){
		
		if(c.equals(User.class)){
			return new UserDAOImpl();
		}else if(c.equals(Advertisement.class)){
			return new AdvertisementDAOImpl();
		}else if(c.equals(Comments.class)){
			return new CommentsDAOImpl();
		}else if(c.equals(Category.class)){
			return new CategoryDAOImpl();
		}else if(c.equals(Location.class)){
			return new LocationDAOImpl();
		}else if(c.equals(FilterLookup.class)){
			return new FilterLookupDAOImpl();
		}else if(c.equals(Pictures.class)){
			return new PicturesDAOImpl();
		}else if(c.equals(PicturesServices.class)){
			return new PicturesServicesDAOImpl();
		}else if(c.equals(Bidding.class)){
			return new BiddingDAOImpl();
		}else if(c.equals(AdvertisementServicesDAO.class)){
			return new AdvertisementServicesDAOImpl();
		}
		
		else{
			return null;
		}
	}
	
}
