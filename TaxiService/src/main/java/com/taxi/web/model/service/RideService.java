package com.taxi.web.model.service;

import java.time.LocalDateTime;
import java.util.List;

import com.taxi.web.model.dao.DaoFactory;
import com.taxi.web.model.dao.RideDao;
import com.taxi.web.model.entity.Ride;

public class RideService {
	
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	
	public List<Ride> getRides(int userId){
		try(RideDao dao = daoFactory.createRideDao()){
			return dao.findRidesByUserId(userId);
		}
	}
	
	public List<Integer> getDayInfo(LocalDateTime timeFrom, LocalDateTime timeTo){
		try(RideDao dao = daoFactory.createRideDao()){
			return dao.infoForADay(timeFrom, timeTo);
		}
	}
	
	public List<Ride> getSortedRides(String column, String order){
		try(RideDao dao = daoFactory.createRideDao()){
			return dao.findAllRides(column, order);
		}
	}
	
	public List<Ride> getRidesByPeriod(LocalDateTime timeFrom, LocalDateTime timeTo){
		try(RideDao dao = daoFactory.createRideDao()){
			return dao.findAllRidesInPeriod(timeFrom, timeTo);
		}
	}
	
	public void addRide(Ride r) {
		try(RideDao dao = daoFactory.createRideDao()){
			dao.addNewRide(r);
		}
	}
	
	
}