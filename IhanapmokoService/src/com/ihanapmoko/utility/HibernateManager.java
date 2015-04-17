package com.ihanapmoko.utility;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;

public class HibernateManager {

	private static final String MYSQL;

	private static SessionFactory MySqlFactory = null;

	static {
		MYSQL = "/com/ihanapmoko/conf/hibernate.cfg.xml";

		MySqlFactory = configureDataSource(MYSQL);
	}

	private static SessionFactory configureDataSource(String DATASOURCE) {

		SessionFactory factory = null;

		try {

			// Create the SessionFactory from hibernate.cfg.xml
			factory = new Configuration().configure(DATASOURCE)
					.buildSessionFactory();
			
		} catch (Throwable ex) {
			ex.printStackTrace();
			// -- Make sure you log the exception, as it might be swallowed --//
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		return factory;
	}

	/* MySQL */
	public static Session getMySql() {
		if (MySqlFactory != null) {
			return MySqlFactory.getCurrentSession();
		} else {
			System.err.println("MySqlFactory is close.");
			return null;
		}
	}

	/* ORACLE */
//	public static Session getOracle() {
//		if (OracleSkeedsFactory != null) {
//			Session thisSession = OracleSkeedsFactory.getCurrentSession();
//			
///*					try {
//						thisSession.connection().prepareStatement("alter session set nls_date_format='MM/DD/YYYY HH:MI:SS am'").execute();
//					} catch (HibernateException e) {
//						e.printStackTrace();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}*/
//			return thisSession;
//		} else {
//			System.err.println("OracleFactory is close.");
//			return null;
//		}
//	}

	public static StatelessSession getStatelessSession() {
		if (MySqlFactory != null) {
			StatelessSession statelessSession = MySqlFactory.openStatelessSession();
			return statelessSession;
		} else {
			throw new SessionException("Session Factory is either null or close...");
		}
	}

	public static void closeFactory() {
/*		if (MySqlFactory != null) {
			MySqlFactory.close();
		}*/

		if (MySqlFactory != null) {
			MySqlFactory.close();
		}

	}
	
}
