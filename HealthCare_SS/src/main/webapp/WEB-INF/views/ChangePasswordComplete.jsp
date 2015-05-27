<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	  <c:choose>
  	  <c:when test="${sessionScope.user.role == 'ROLE_NURSE'}">
   			 <%@include file="MenuNurse.jsp"%> 
      </c:when>
      <c:when test="${sessionScope.user.role == 'ROLE_DOCTOR'}">
   			 <%@include file="MenuDoctor.jsp"%> 
      </c:when>
      <c:when test="${sessionScope.user.role == 'ROLE_PATIENT'}">
   			 <%@include file="MenuPatient.jsp"%> 
      </c:when>
       <c:when test="${sessionScope.user.role == 'ROLE_BILLING'}">
   			 <%@include file="MenuBilling.jsp"%> 
      </c:when>
     </c:choose> 
     
     <div style="float:left; margin-left:200px; ">
     <c:choose>
  	  <c:when test="${error == '1'}">
   			<h1>Failed because of wrong password</h1>
      </c:when>
      <c:when test="${error == '0'}">
   			<h1>Password change successful</h1>
      </c:when>
     </c:choose> 
     
    </div>
	
		
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
	
	
</body>
</html>