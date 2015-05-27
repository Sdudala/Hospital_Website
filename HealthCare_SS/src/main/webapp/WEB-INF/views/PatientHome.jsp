<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Login Page.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
</head>

<body style="background-image: none;">
	
	<%@include file="MenuPatient.jsp"%> 
	
	
	<div style="float:left">
		<h1 id="h1">Welcome ${sessionScope.user.person.firstName} to Patient Work Area</h1>
	</div>
	<div class="clear"></div>
	
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
</body>
</html>