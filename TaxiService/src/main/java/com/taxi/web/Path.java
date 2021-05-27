package com.taxi.web;
public final class Path {
	
	// pages
	public static final String PAGE_INDEX = "/";
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_REGISTER = "/register.jsp";
	public static final String PAGE_ERROR = "WEB-INF/jsp/error_page.jsp";
	
	public static final String PAGE_ACCOUNT = "/WEB-INF/jsp/client/account.jsp";
	public static final String PAGE_RIDE = "/WEB-INF/jsp/client/ride.jsp";
	public static final String PAGE_RIDE_DETAILS = "/WEB-INF/jsp/client/rideDetails.jsp";
	public static final String PAGE_RIDE_CONFIRMED = "/WEB-INF/jsp/client/rideConfirmed.jsp";
	
	
	public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin/adminPage.jsp";
	public static final String PAGE_ORDERS = "/WEB-INF/jsp/admin/orders.jsp";
	

	// commands
	public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
	public static final String COMMAND__LIST_MENU = "/controller?command=listMenu";

}