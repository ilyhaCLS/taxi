<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.taxi.web.model.entity.Ride"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<fmt:setBundle basename="resources" />

<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<p>STATS PAGE</p>

	<h1>
	Rides in a 24h period: <c:out value="${ridesInADay}"/>
		<br />
	</h1>
	
	<h1>
	Income in a 24h period:<c:out value="${sumInADay}"/>
		<br />
	</h1>


	<h3>Show All Rides :</h3>

	<h3>
		<a href="controller?command=showRides&q=all&order=desc"
			target="_blank">New first</a>
	</h3>
	<h3>
		<a href="controller?command=showRides&q=all&order=asc" target="_blank">Old
			first</a>
	</h3>
	<h3>
		<a href="controller?command=showRides&q=all&order=exp" target="_blank">Expensive
			first</a>
	</h3>
	<h3>
		<a href="controller?command=showRides&q=all&order=cheap"
			target="_blank">Cheap first</a>
	</h3>


	<div class="col s3">
		<form action="controller?command=showRides&q=all&order=date"
			method="post">
			<p>
				<b>Сделаные в период с (включительно) ?</b>
			</p>
			<input type="datetime-local" name="from" required="required">

			<p>
				<b>по (включительно) ?</b>
			</p>
			<input type="datetime-local" name="until" required="required">

			<button type="submit" name="show">Показать</button>
		</form>
	</div>
	
	
		<form action="controller?command=showRides&q=us" method="post">
			<p>
				<b>По id пользователя</b>
			</p>
			<input type="number" name="user_id" required="required">


			<button type="submit" name="show">Показать</button>
		</form>
</body>
</html>