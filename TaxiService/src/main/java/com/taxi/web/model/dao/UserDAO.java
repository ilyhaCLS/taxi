package com.taxi.web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.taxi.web.model.entity.User;
import com.taxi.web.model.entity.UserInfo;
import com.taxi.web.model.entity.User.UserBuilder;

public class UserDAO {

	DBManager dbManager = DBManager.getInstance();

	public boolean regNewUser(User us, UserInfo usInfo) throws SQLException {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatementUS = con.prepareStatement(
						"INSERT INTO user (login, password, salt) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				PreparedStatement preStatementUSInfo = con
						.prepareStatement("INSERT INTO user_info (user_id, first, last) VALUES(?, ?, ?)")) {

			preStatementUS.setString(1, us.getLogin());
			preStatementUS.setString(2, us.getPassword());
			preStatementUS.setBytes(3, us.getSalt());
			preStatementUS.execute();
			ResultSet rs = preStatementUS.getGeneratedKeys();

			if (rs.next()) {
				preStatementUSInfo.setInt(1, rs.getInt(1));
			} else {
				throw new SQLException();
			}

			preStatementUSInfo.setString(2, usInfo.getFirst());
			preStatementUSInfo.setString(3, usInfo.getLast());
			preStatementUSInfo.execute();

			rs.close();
			return true;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	public User findUserByLogin(String login) {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con.prepareStatement("SELECT * FROM user WHERE login = ?")) {
			preStatement.setString(1, login);
			ResultSet rs = preStatement.executeQuery();
			User us = null;
			if (rs.next()) {
				us = new UserBuilder().setId(rs.getInt(1)).setLogin(rs.getString(2)).setPassword(rs.getString(3))
						.setRole(rs.getString(4)).setSalt(rs.getBytes(5)).build();
			}
			rs.close();
			return us;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	
	public String findFirstNameByUserId(int id) {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con
						.prepareStatement("SELECT first FROM user_info WHERE user_id = ?")) {
			preStatement.setInt(1, id);
			ResultSet rs = preStatement.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			rs.close();
			return null;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	public UserInfo findUserInfoByUserId(int id) {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con
						.prepareStatement("SELECT first, last, total_spent FROM user_info WHERE user_id = ?")) {
			UserInfo usInfo = new UserInfo();

			preStatement.setInt(1, id);
			ResultSet rs = preStatement.executeQuery();
			if (rs.next()) {
				usInfo.setFirst(rs.getString(1));
				usInfo.setLast(rs.getString(2));
				usInfo.setTotalSpent(Integer.parseInt(rs.getString(3)));
			}
			rs.close();
			return usInfo;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int findTotalSpentByUserId(int id) {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con
						.prepareStatement("SELECT total_spent FROM user_info WHERE user_id = ?")) {
			int res = 0;
			preStatement.setInt(1, id);
			ResultSet rs = preStatement.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
			rs.close();
			return res;
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		}
	}
}