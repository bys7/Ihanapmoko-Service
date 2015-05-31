package com.ihanapmoko.dao;

import java.util.List;

import com.ihanapmoko.bean.Category;

public interface CategoryDAO {
	
	public List<Category> getAllCategory();
	public Category getCategoryById(int id);

}
