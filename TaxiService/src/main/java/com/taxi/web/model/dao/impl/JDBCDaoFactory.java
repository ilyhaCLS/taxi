package com.taxi.web.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taxi.web.model.dao.CarDao;
import com.taxi.web.model.dao.DaoFactory;
import com.taxi.web.model.dao.RideDao;
import com.taxi.web.model.dao.UserDao;

public class JDBCDaoFactory extends DaoFactory {
	
	Context initContext = null;
	Context envContext  = null;
	
	DataSource ds = null;
	
	public JDBCDaoFactory(){
		try {
			initContext = new InitialContext();
			envContext  = (Context)initContext.lookup("java:/comp/env");
			
			// ST4DB - the name of data source
			ds = (DataSource)envContext.lookup("jdbc/taxi");
		} catch (NamingException ex) {
			System.out.println(ex);
		}
	}
	
	private Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	@Override
    public RideDao createRideDao() {
		return new JDBCRideDao(getConnection());
    }
    @Override
    public CarDao createCarDao() {
		return new JDBCCarDao(getConnection());
    }
	@Override
	public UserDao createUserDao() {
		return new JDBCUserDao(getConnection());
	}
}