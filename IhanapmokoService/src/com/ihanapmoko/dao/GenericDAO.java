package com.ihanapmoko.dao;

import java.util.Collection;

public interface GenericDAO {
	
	public boolean save(Object o);

	public boolean edit(Object o);

	public boolean delete(Object o);

	public Object findById(Long id);

	public Collection<?> findAll(String sql);

}
