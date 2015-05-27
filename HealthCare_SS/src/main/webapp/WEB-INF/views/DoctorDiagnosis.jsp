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
	
	<%@include file="MenuDoctor.jsp"%> 
		
	<div style="float:left; margin-left:200px; ">
    
    <form:form name="updateDiagnosis" action="doctorSubmit" method="post" class="registration-form" id="Myform" commandName="appointment">	
    
   	<input type="hidden" name="apptId" value="${apptId}" />	
    
    <div class="form-group">
		<label  class="col-sm-6 control-label">Complaint : ${appt.complaint}</label>
	</div>
   
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
  	
  	 <div class="form-group">
		<label  class="col-sm-4 control-label">Diagnosis</label>
		<div class="row"><div class="col-md-8">
			<form:input path="diagnosis" type="text" id="textbox" class="form-control" />
			<form:errors path="diagnosis" cssStyle="color:#ff0000"/></div></div>
	 </div>
	 
	 <div class="form-group">
	 <h3>Add Prescription if necessary</h3>
	 <c:forEach begin="0" end="2" varStatus="loop">
          Index: ${loop.index}<br/>
          Drug: 
	       <select name="drug${loop.index}">
            <option value="Tylenol">Tylenol</option>
            <option value="Aspirin">Aspirin</option>
            <option value="Ibuprofen">Ibuprofen</option>
            <option value="Xanax">Xanax</option>
            <option value="Vicodin">Vicodin</option>
           </select>
           &nbsp;&nbsp;&nbsp;
           Quantity:
           <input type="number" name="dosage${loop.index}" id="maxyy" min="0" max="25" step="1" value="0">
           <br/><br/>
     </c:forEach>
	 	 
	 </div>
	 
	 	 
	 <input type="submit" value="Submit" class="btn btn-success btn-lg" style="font-weight:bold"/>
    </form:form>
	
	</div>
	
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 	
	
</body>
</html>