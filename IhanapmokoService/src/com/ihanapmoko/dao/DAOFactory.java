package com.ihanapmoko.dao;

import com.ihanapmoko.bean.Advertisement;
import com.ihanapmoko.bean.Category;
import com.ihanapmoko.bean.Comments;
import com.ihanapmoko.bean.Location;
import com.ihanapmoko.bean.User;
import com.ihanapmoko.daoimpl.AdvertisementDAOImpl;
import com.ihanapmoko.daoimpl.CategoryDAOImpl;
import com.ihanapmoko.daoimpl.CommentsDAOImpl;
import com.ihanapmoko.daoimpl.GenericDAOImpl;
import com.ihanapmoko.daoimpl.LocationDAOImpl;
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
		}
		
		else{
			return null;
		}
	}
	
}
