package com.taxi.web.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.taxi.web.model.entity.Ride;

public interface RideDao extends GenericDao<Ride> {
	
	public List<Ride> findRidesByUserId(int id);
	
	public List<Integer> infoForADay(LocalDateTime timeFrom, LocalDateTime timeTo);
	
	public List<Ride> findAllRides(String column, String order);
	
	public List<Ride> findAllRidesInPeriod(LocalDateTime timeFrom, LocalDateTime timeTo);
	
	public boolean addNewRide(Ride ride);
}