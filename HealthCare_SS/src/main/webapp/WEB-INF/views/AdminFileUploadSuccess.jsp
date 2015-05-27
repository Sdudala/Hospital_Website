<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Upload Successful</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Login Page.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<script src="${pageContext.request.contextPath}/resources/css/jquery-1.10.2.js"></script>
</head>

<body style="background-image: none;">

	<%@include file="MenuAdmin.jsp"%> 
	
	<div style="float:left; margin-left:200px; ">	
	 
	FileName : "
	<strong> ${fileName} </strong>" - Uploaded Successful.
	
	</div>
		
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%>
 
</body>
</html>