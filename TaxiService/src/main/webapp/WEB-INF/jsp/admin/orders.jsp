<%@page import="java.util.Arrays"%>
<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.taxi.web.model.entity.Ride"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>
    
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>

<table class="table table-hover table-dark">
		<thead>
			<tr>
				<th scope="col">id</th>
				<th scope="col"><fmt:message key="admin.rides.user_id"/></th>
				<th scope="col"><fmt:message key="account.rides.from"/></th>
				<th scope="col"><fmt:message key="account.rides.to"/></th>
				<th scope="col"><fmt:message key="account.rides.price"/></th>
				<th scope="col"><fmt:message key="account.rides.date"/></th>
				<th scope="col"><fmt:message key="account.rides.car"/></th>
			</tr>
		</thead>
		<tbody>

			<%
			
			
			ArrayList<Ride> rides = (ArrayList<Ride>) request.getAttribute("rides");
			
			String langTag = Arrays.stream(request.getCookies())
					.filter((Cookie c)->c.getName().equals("lang")).findFirst().get().getValue();
			
			LocalDateTime dt = null;
		    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
		            .withLocale(Locale.forLanguageTag(langTag));

			for (Ride ride : rides) {
				dt = LocalDateTime.parse(ride.getCreationTime().toString());
				out.println("<tr><th scope=\"row\">"+ride.getId()+"</th>");
				out.println("<td>"+ride.getUserId() +"</td>");
				out.println("<td>"+ride.getPosFrom() +"</td>");
				out.println("<td>"+ride.getPosTo() +"</td>");
				out.println("<td>"+ride.getPrice() +"</td>");
				out.println("<td>"+dt.format(formatter) +"</td>");
				out.println("<td>"+ride.getCar().getLicPlate()+" "+ride.getCar().getName()+" "+ride.getCar().getCarClass() +"</td></tr>");
			}
			%>
		</tbody>
	</table>

</body>
</html>