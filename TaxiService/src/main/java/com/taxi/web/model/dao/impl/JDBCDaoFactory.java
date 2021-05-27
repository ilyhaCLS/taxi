package com.taxi.web.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taxi.web.model.dao.DAOFactory;
import com.taxi.web.model.dao.UserDAO;

public class JDBCDaoFactory extends DAOFactory {
	
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
	
	
	
	@Override
    public RideDao createRideDao() {
        return new JDBCRideDao(getConnection());
    }
    @Override
    public CarDao createCarDao() {
        return new JDBCCarDao(getConnection());
    }
	@Override
	public UserDAO createUserDao() {
		return JDBCUserDao(getConnection());
	}
}