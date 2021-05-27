package com.taxi.web.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.taxi.web.model.dao.RideDao;
import com.taxi.web.model.entity.Car;
import com.taxi.web.model.entity.Ride;
import com.taxi.web.model.entity.Ride.RideBuilder;

public class JDBCRideDao implements RideDao {

	private Connection connection;


    public JDBCRideDao(Connection connection) {
        this.connection = connection;
    }
	
	
	@Override
	public void create(Ride entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ride findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ride> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ride entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}


	@Override
	public List<Ride> findRidesByUserId(int id) {
		try (PreparedStatement preStatement = connection.prepareStatement(
						"SELECT * FROM ride r JOIN car c ON r.car_id = c.id WHERE r.user_id = ? ORDER BY creation_time DESC")) {

			List<Ride> rides = new ArrayList<>();
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


	@Override
	public List<Integer> infoForADay(LocalDateTime timeFrom, LocalDateTime timeTo) {
		List<Integer> res = new ArrayList<>();
		try (PreparedStatement preStatement = connection
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


	@Override
	public List<Ride> findAllRides(String column, String order) {
		try (PreparedStatement preStatement = connection.prepareStatement(
						"SELECT * FROM ride r JOIN car c ON r.car_id = c.id ORDER BY " + column + " " + order)) {

			List<Ride> rides = new ArrayList<>();
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


	@Override
	public List<Ride> findAllRidesInPeriod(LocalDateTime timeFrom, LocalDateTime timeTo) {
		try (PreparedStatement preStatement = connection.prepareStatement(
						"SELECT * FROM ride r JOIN car c ON r.car_id = c.id WHERE creation_time >= ? AND creation_time <= ?")) {

			System.out.println(timeFrom);
			System.out.println(timeTo);

			preStatement.setString(1, timeFrom.toString());
			preStatement.setString(2, timeTo.toString());
			ResultSet rs = preStatement.executeQuery();

			List<Ride> rides = new ArrayList<>();

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


	@Override
	public boolean addNewRide(Ride ride) {
		try (PreparedStatement preStatement = connection
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