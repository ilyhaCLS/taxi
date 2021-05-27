package com.taxi.web.model.dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.taxi.web.model.entity.Car;
import com.taxi.web.model.entity.Ride;

public class MainDAO {

	DBManager dbManager = DBManager.getInstance();

	public synchronized Car findCarByClassAndAddNewRide(String carClass, Ride ride) {
		Connection con = null;
		try {
			con = dbManager.getConnection();

			PreparedStatement preStatement = con
					.prepareStatement("SELECT id, lic_plate, name FROM car WHERE class = ? AND status = 'ACTIVE'");

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
			
			preStatement.close();
			rs.close();

			c = cars.get(cars.size() > 1 ? new SecureRandom().nextInt(cars.size()) : 0);

			preStatement = con.prepareStatement("INSERT INTO ride (pos_from, pos_to, price, creation_time, car_id, user_id)"
							+ " VALUES (?, ?, ?, ?, ?, ?)");
			
			preStatement.setString(1, ride.getPosFrom());
			preStatement.setString(2, ride.getPosTo());
			preStatement.setInt(3, ride.getPrice());
			preStatement.setString(4, LocalDateTime.now().toString());
			preStatement.setInt(5, c.getId());
			preStatement.setInt(6, ride.getUserId());
			
			preStatement.execute();
			con.close();
			
			return c;
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(e);
			return null;
		}
	}

	public boolean addNewRide(Ride ride) {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con
						.prepareStatement("INSERT INTO ride (pos_from, pos_to, price, creation_time, car_id, user_id)"
								+ " VALUES (?, ?, ?, ?, ?, ?)")) {
			preStatement.setString(1, ride.getPosFrom());
			preStatement.setString(2, ride.getPosTo());
			preStatement.setInt(3, ride.getPrice());
			preStatement.setString(4, ride.getCreationTime().toString());
			preStatement.setInt(5, ride.getCarId());
			preStatement.setInt(6, ride.getUserId());

			preStatement.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
}