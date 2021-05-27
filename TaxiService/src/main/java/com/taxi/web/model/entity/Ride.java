package com.taxi.web.model.entity;

import java.time.LocalDateTime;

public class Ride {
	private int id;
	private String posFrom;
	private String posTo;
	private int price;
	private LocalDateTime creationTime;
	private int carId;
	private int userId;
	private Car car;
	
	
	private Ride(int id, String posFrom, String posTo, int price, LocalDateTime creationTime, int carId, int userId,
			Car car) {
		super();
		this.id = id;
		this.posFrom = posFrom;
		this.posTo = posTo;
		this.price = price;
		this.creationTime = creationTime;
		this.carId = carId;
		this.userId = userId;
		this.car = car;
	}

	
	public int getId() {
		return id;
	}
	
	public String getPosFrom() {
		return posFrom;
	}
	
	public String getPosTo() {
		return posTo;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	
	public int getCarId() {
		return carId;
	}
	
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public Car getCar() {
		return car;
	}
	
	public static class RideBuilder{
		private int id;
		private String posFrom;
		private String posTo;
		private int price;
		private LocalDateTime creationTime;
		private int carId;
		private int userId;
		private Car car;
		
		public RideBuilder setId(int id) {
			this.id = id;
			return this;
		}
		
		public RideBuilder setPosFrom(String posFrom) {
			this.posFrom = posFrom;
			return this;
		}
		
		public RideBuilder setPosTo(String posTo) {
			this.posTo = posTo;
			return this;
		}
		
		public RideBuilder setPrice(int price) {
			this.price = price;
			return this;
		}
		
		public RideBuilder setCreationTime(LocalDateTime creationTime) {
			this.creationTime = creationTime;
			return this;
		}
		
		public RideBuilder setCarId(int carId) {
			this.carId = carId;
			return this;
		}
		
		public RideBuilder setUserId(int userId) {
			this.userId = userId;
			return this;
		}
		
		public RideBuilder setCar(Car car) {
			this.car = car;
			return this;
		}
		
		public Ride build() {
			return new Ride(id, posFrom, posTo, price, creationTime, carId, userId, car); 
		}
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carId;
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((posFrom == null) ? 0 : posFrom.hashCode());
		result = prime * result + id;
		result = prime * result + price;
		result = prime * result + userId;
		result = prime * result + ((posTo == null) ? 0 : posTo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Car))
			return false;
		
		Ride other = (Ride) obj;
		if(!posFrom.equals(other.getPosFrom())) {
			return false;
		}else if (!posTo.equals(other.getPosTo())){
			return false;
		}else if (price != other.getPrice()) {
			return false;
		}else if (!creationTime.equals(other.getCreationTime())) {
			return false;
		}else if (carId != other.getCarId()) {
			return false;
		}else if(userId != other.getUserId()) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", posFrom=" + posFrom + ", posFrom=" + posFrom + ", price=" + price + ", creationTime="
				+ creationTime + ", carId=" + carId + ", userId=" + userId + "]";
	}
}