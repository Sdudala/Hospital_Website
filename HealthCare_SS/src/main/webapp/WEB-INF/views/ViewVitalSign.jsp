<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Request Appointment</title>
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
     </c:choose> 
	
    <div style="float:left; margin-left:200px; ">
    
    <form:form>	
	<table class="table table-hover table-bordered center">
			
		<tr>
			<td><b>Temp</b></td>
			<td><b>Heart Rate</b></td>
			<td><b>Resp Rage</b></td>
			<td><b>BP</b></td>
			<td><b>Weight(lbs)</b></td>
		</tr>
		<tr>
			<td>${appt.vitalSigns.temperature}</td>
			<td>${appt.vitalSigns.heartRate}</td>
			<td>${appt.vitalSigns.respRate}</td>
			<td>${appt.vitalSigns.bloodPressure}</td>
			<td>${appt.vitalSigns.weightPounds}</td>
		</tr>
   	</table>
    </form:form>
    
    <form><input Type="button" VALUE="Back" onClick="history.go(-1);return true;"></form>
	
	</div>
	
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 

	
</body>
</html>
