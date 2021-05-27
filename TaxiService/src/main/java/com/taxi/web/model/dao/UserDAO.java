package com.taxi.web.model.dao;

import java.sql.SQLException;

import com.taxi.web.model.entity.User;
import com.taxi.web.model.entity.UserInfo;

public interface UserDao extends GenericDao<User> {
	
	public boolean regNewUser(User us, UserInfo usInfo) throws SQLException;
	
	public User findUserByLogin(String login);
	
	public String findFirstNameByUserId(int id);
	
	public UserInfo findUserInfoByUserId(int id);
	
	public int findTotalSpentByUserId(int id);
}