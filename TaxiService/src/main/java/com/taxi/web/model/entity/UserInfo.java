package com.taxi.web.model.entity;

public class UserInfo {
	private int userId;
	private String first;
	private String last;
	private int totalSpent;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public int getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(int totalSpent) {
		this.totalSpent = totalSpent;
	}
	
	
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", first=" + first + ", last=" + last + ", totalSpent=" + totalSpent
				+ "]";
	}
}