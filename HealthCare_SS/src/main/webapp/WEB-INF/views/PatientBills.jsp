<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    
      <%@include file="MenuPatient.jsp"%> 

	 <div style="float:left; margin-left:200px; ">
    
    <%-- <form:form>	 --%>
	<table class="table table-hover table-bordered center">
			
		<tr>
			<td><b>Appointment ID</b></td>
			<td><b>Date</b></td>
			<td><b>Patient</b></td>
			<td><b>Doctor</b></td>
			<td><b>Complaint</b></td>
			<td><b>Bill</b></td>
		</tr>
	<c:forEach var="appt" items="${apptList}">
		<tr>
			<td>${appt.appointmentId}</td>
			<td><fmt:formatDate value="${appt.date}" type="both" pattern="MM-dd-yyyy" /></td>
			<td>${appt.patient.firstName}&nbsp;${appt.patient.lastName} </td>
			<td>${appt.doctor.person.firstName}&nbsp;${appt.doctor.person.lastName} </td>
			<td>${appt.complaint}</td>
			<td>
			<form:form action="patientBillReview" method="post">
              <input type="Submit" value="Pay"/>
              <input type=hidden name=apptId value="${appt.appointmentId}">
            </form:form>
            </td>
		</tr>
  	</c:forEach>
  	</table>
   <%--  </form:form> --%>
	
	</div>
		
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
</body>
</html>
