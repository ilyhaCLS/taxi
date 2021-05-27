package com.taxi.web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.taxi.web.model.entity.Car;
import com.taxi.web.model.entity.Ride;
import com.taxi.web.model.entity.Ride.RideBuilder;

public class RideDAO {

	DBManager dbManager = DBManager.getInstance();

	public ArrayList<Ride> findRidesByUserId(int id) {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con.prepareStatement(
						"SELECT * FROM ride r JOIN car c ON r.car_id = c.id WHERE r.user_id = ? ORDER BY creation_time DESC")) {

			ArrayList<Ride> rides = new ArrayList<>();
			Ride ride = null;
			preStatement.setInt(1, id);
			ResultSet rs = preStatement.executeQuery();
			while (rs.next()) {
				ride = new RideBuilder().setId(rs.getInt(1)).setPosFrom(rs.getString(2)).setPosTo(rs.getString(3))
						.setPrice(rs.getInt(4))
						.setCreationTime(LocalDateTime.parse(rs.getString(5),
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
						.setUserId(rs.getInt("user_id"))
						.setCar(new Car(rs.getString("lic_plate"), rs.getString("class"), rs.getString("name")))
						.build();
				rides.add(ride);
			}
			rs.close();
			return rides;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ArrayList<Integer> infoForADay(LocalDateTime timeFrom, LocalDateTime timeTo) {
		ArrayList<Integer> res = new ArrayList<>();
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con
						.prepareStatement("SELECT price FROM ride WHERE creation_time >= ? AND creation_time <= ?")) {
			preStatement.setString(1, timeFrom.toString());
			preStatement.setString(2, timeTo.toString());
			ResultSet rs = preStatement.executeQuery();

			while (rs.next()) {
				res.add(rs.getInt(1));
			}
			return res;
		} catch (SQLException e) {
			System.out.println(e);
			return res;
		}
	}

	public ArrayList<Ride> findAllRides(String column, String order) {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con.prepareStatement(
						"SELECT * FROM ride r JOIN car c ON r.car_id = c.id ORDER BY " + column + " " + order)) {

			ArrayList<Ride> rides = new ArrayList<>();
			Ride ride = null;
			ResultSet rs = preStatement.executeQuery();
			while (rs.next()) {
				ride = new RideBuilder().setId(rs.getInt(1)).setPosFrom(rs.getString(2)).setPosTo(rs.getString(3))
						.setPrice(rs.getInt(4))
						.setCreationTime(LocalDateTime.parse(rs.getString(5),
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
						.setUserId(rs.getInt("user_id"))
						.setCar(new Car(rs.getString("lic_plate"), rs.getString("class"), rs.getString("name")))
						.build();

				rides.add(ride);
			}
			rs.close();
			return rides;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	public ArrayList<Ride> findAllRidesInPeriod(LocalDateTime timeFrom, LocalDateTime timeTo) {
		try (Connection con = dbManager.getConnection();
				PreparedStatement preStatement = con.prepareStatement(
						"SELECT * FROM ride r JOIN car c ON r.car_id = c.id WHERE creation_time >= ? AND creation_time <= ?")) {

			System.out.println(timeFrom);
			System.out.println(timeTo);

			preStatement.setString(1, timeFrom.toString());
			preStatement.setString(2, timeTo.toString());
			ResultSet rs = preStatement.executeQuery();

			ArrayList<Ride> rides = new ArrayList<>();

			Ride ride = null;
			while (rs.next()) {
				ride = new RideBuilder().setId(rs.getInt(1)).setPosFrom(rs.getString(2)).setPosTo(rs.getString(3))
						.setPrice(rs.getInt(4))
						.setCreationTime(LocalDateTime.parse(rs.getString(5),
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
						.setUserId(rs.getInt("user_id"))
						.setCar(new Car(rs.getString("lic_plate"), rs.getString("class"), rs.getString("name")))
						.build();
				rides.add(ride);
			}
			rs.close();
			return rides;
		} catch (SQLException e) {
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
			preStatement.setString(4, LocalDateTime.now().toString());
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