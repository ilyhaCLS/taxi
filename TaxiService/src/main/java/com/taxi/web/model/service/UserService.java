package com.taxi.web.model.service;

import com.taxi.web.model.dao.UserDAO;

public class UserService {
	
	DAOFactory daoFactory = DAOFactory.getInstance();
	
	public int getTotalSpent(int userId){
		try(UserDAO userDAO = daoFactory.createUserDAO()){
			
		}
	}
}