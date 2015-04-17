package com.ihanapmoko.daoimpl;

import org.hibernate.Session;

import com.ihanapmoko.bean.Comments;
import com.ihanapmoko.dao.CommentsDAO;

public class CommentsDAOImpl extends GenericDAOImpl implements CommentsDAO {

	public boolean create(Comments comments){
		
		boolean result = false;
		
		try{
			if(comments!=null){
				return save(comments);
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
