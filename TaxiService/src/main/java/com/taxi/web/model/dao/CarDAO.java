package com.taxi.web.model.dao;

import com.taxi.web.model.entity.Car;

public interface CarDao extends GenericDao<Car> {

	public Car findActiveCarByClass(String carClass);
}