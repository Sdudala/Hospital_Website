<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<%@include file="MenuNurse.jsp"%> 

    <div style="float:left; margin-left:100px; ">
	<form:form name="vitalSignsForm" action="nurseSubmit" method="post" class="registration-form" commandName="appointment">
            
            <h3>Add Vital Signs</h3>
            <input type="hidden" name="apptId" value="${apptId}" />
			
			<div class="form-group">
				<label  class="col-sm-4 control-label">Temperature</label>
					<div class="row"><div class="col-md-6">
					<form:input path="vitalSigns.temperature" type="number" id="textbox" class="form-control" />
					<form:errors path="vitalSigns.temperature" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-4 control-label">Resp Rate</label>
					<div class="row"><div class="col-md-6">
					<form:input path="vitalSigns.respRate" type="number" id="textbox" class="form-control" />
					<form:errors path="vitalSigns.respRate" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-4 control-label">Heart Rate</label>
					<div class="row"><div class="col-md-6">
					<form:input path="vitalSigns.heartRate" type="number" id="textbox" class="form-control" />
					<form:errors path="vitalSigns.heartRate" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-4 control-label">Blood Pressure</label>
					<div class="row"><div class="col-md-6">
					<form:input path="vitalSigns.bloodPressure" type="number" id="textbox" class="form-control" />
					<form:errors path="vitalSigns.bloodPressure" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-4 control-label">Weight(lb)</label>
					<div class="row"><div class="col-md-6">
					<form:input path="vitalSigns.weightPounds" type="number" id="textbox" class="form-control" />
					<form:errors path="vitalSigns.weightPounds" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<input type="submit" value="Submit" class="btn btn-success btn-lg" style="font-weight:bold"/>
			
	</form:form>
	</div>
	
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
	
</body>
</html>