package com.ihanapmoko.daoimpl;

import com.ihanapmoko.bean.PicturesServices;
import com.ihanapmoko.dao.PicturesServicesDAO;

public class PicturesServicesDAOImpl extends GenericDAOImpl implements PicturesServicesDAO{

	public boolean create(PicturesServices picturesServices){
		boolean result = false;
		
		try{
			if(picturesServices!=null){				
				return save(picturesServices);
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession();
		}
		
		return result;
	}
	
}
