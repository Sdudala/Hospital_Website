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
    
    <form:form name="appointment-form" action="patientSubmitAppt" method="post" class="registration-form" commandName="appointment">
            
            <h3>Request Appointment</h3>
			
			<div class="form-group">
				<label  class="col-sm-4 control-label">Date</label>
					<div class="row"><div class="col-sm-6">
					<fmt:formatDate value="${existedEvent.date}" pattern="MM-dd-yyyy" var="formattedDate"/>
   					<form:input path="date" type="date" id="textbox" class="form-control" value = "${formattedDate}" required="true"/>(MM-dd-yyyy)
					<form:errors path="date" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-4 control-label">Complaint</label>
					<div class="row"><div class="col-sm-6">
					<form:input path="complaint" type="text" id="textbox" class="form-control" placeholder="Complaint" required="true"/>
					<form:errors path="complaint" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
               <label for="doctorId" class="col-sm-4 control-label">Doctor</label>
               <form:select path="doctorId">
                  <%--   <form:option value="" label="" />   Do not uncomment this--%>
                   <form:options items="${doctorList}" itemValue="userId" />
               </form:select>
            </div>
			
			<input type="submit" value="Submit" class="btn btn-success btn-lg" style="font-weight:bold"/>
	</form:form>
    
  	</div>

	
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
	
</body>
</html>
