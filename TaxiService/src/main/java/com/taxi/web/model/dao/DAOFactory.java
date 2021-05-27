package com.taxi.web.model.dao;

import com.taxi.web.model.dao.impl.JDBCDaoFactory;

public abstract class DAOFactory {
	private static DAOFactory daoFactory;
	
	public abstract UserDAO createUserDao();
    public abstract RideDAO createRideDao();
    public abstract CarDAO createCarDao();
	
	public static DAOFactory getInstance(){
		if( daoFactory == null ){
            synchronized (DAOFactory.class){
                if(daoFactory==null){
                    DAOFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
	}
}