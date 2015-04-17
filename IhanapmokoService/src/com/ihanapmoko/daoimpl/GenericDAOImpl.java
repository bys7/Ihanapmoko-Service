package com.ihanapmoko.daoimpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.ihanapmoko.dao.GenericDAO;
import com.ihanapmoko.utility.HibernateManager;

public class GenericDAOImpl implements GenericDAO{

	private Session session = null;
	private Class<?> entityClass = null;
	private boolean dberror = false;

	public GenericDAOImpl() {}
	
	public GenericDAOImpl(Class<?> c) {
		entityClass = c;
	}
	
	public boolean save(Object o) {
		boolean result = false;
		
		try {
			setSession(HibernateManager.getMySql());

			getSession().beginTransaction();
			getSession().save(o);
			getSession().flush();
			getSession().getTransaction().commit();

			result = true;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			closeSession();
		}
		
		return result;
	}

	public boolean edit(Object o) {
		boolean result = false;

		try {
			setSession(HibernateManager.getMySql());

			getSession().beginTransaction();
			getSession().merge(o);
			getSession().flush();
			getSession().getTransaction().commit();

			result = true;

		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			closeSession();
		}

		// TODO Auto-generated method stub
		return result;
	}

	public boolean delete(Object o) {

		boolean result = false;

		try {
			setSession(HibernateManager.getMySql());

			getSession().beginTransaction();
			
			getSession().clear();//clear session to prevent exception
			
			getSession().delete(o);
			getSession().flush();
			getSession().getTransaction().commit();

			result = true;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			closeSession();
		}
		return result;
	}

	public Object findById(Long id) {
		Object o = null;

		try {
			setSession(HibernateManager.getMySql());

			getSession().beginTransaction();
			o = getSession().get(entityClass, id);
			getSession().getTransaction().commit();

		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}finally{
			closeSession();
		}

		return o;
	}

	public List<?> findAll(String sql) {
		
		List<?> list = null;
		
		try {
			setSession(HibernateManager.getMySql());
			getSession().beginTransaction();
			list = getSession().createQuery(sql).list();
			getSession().getTransaction().commit();

		} catch (org.hibernate.TransactionException he) {
			dberror = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		
		return list;
	}

	protected void closeSession() {
		if (getSession() != null && getSession().isOpen()) {
			getSession().close();
		}
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	
	public boolean isDberror() {
		return dberror;
	}
	
	public void session_rollback(){
		session.getTransaction().rollback();
	}
	

	public long getCount(String table) {
		long count = 0;
		Session session = null;
		List<Long> transList = null;
		
		try{
			session = HibernateManager.getMySql();
			session.beginTransaction();			
			
			SQLQuery sqlQuery = session.createSQLQuery(" SELECT  COUNT(*) FROM " + table)										
										.addScalar("COUNT(*)", Hibernate.LONG);
				transList = sqlQuery.list();
				session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			for(int i=0; i<transList.size();i++){
				count = transList.get(i);
			}
			closeSession();
		}
		
		return count;
	}
	
	
}
