package com.taxi.web.model.dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taxi.web.model.entity.Car;

public class CarDAO {

	DBManager dbManager = DBManager.getInstance();

	public Car findActiveCarByClass(String carClass) {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con.prepareStatement(
						"SELECT id, lic_plate, name FROM car WHERE class = ? AND status = 'ACTIVE'")) {

			preStatement.setString(1, carClass);
			List<Car> cars = new ArrayList<>();
			Car c = null;

			ResultSet rs = preStatement.executeQuery();
			while (rs.next()) {
				c = new Car();
				c.setId(rs.getInt("id"));
				c.setLicPlate(rs.getString("lic_plate"));
				c.setName(rs.getString("name"));
				cars.add(c);
			}
			
			rs.close();

			c = cars.get(cars.size() > 1 ? new SecureRandom().nextInt(cars.size()) : 0);

			return c;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
}