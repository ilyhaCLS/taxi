package com.taxi.web.model.dao;

import com.taxi.web.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
	private static DaoFactory daoFactory;
	
	public abstract UserDao createUserDao();
    public abstract RideDao createRideDao();
    public abstract CarDao createCarDao();
	
	public static DaoFactory getInstance(){
		if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
	}
}