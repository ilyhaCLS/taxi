package com.taxi.web.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {

	private static DBManager dbManager = null;
	
	public static synchronized DBManager getInstance() {
		if (dbManager == null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}

	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			
			// ST4DB - the name of data source
			DataSource ds = (DataSource)envContext.lookup("jdbc/taxi");
			con = ds.getConnection();
		} catch (NamingException ex) {
			System.out.println(ex);			
		}
		return con;
	}
}