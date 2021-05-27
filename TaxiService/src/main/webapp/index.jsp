<%@page import="java.io.*"%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<fmt:setLocale value="${empty cookie.lang.value ? 'ru': cookie.lang.value}" />

<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf" %>
</body>
</html>