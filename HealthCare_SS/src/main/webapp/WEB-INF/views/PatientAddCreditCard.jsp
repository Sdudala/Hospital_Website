<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    <%@include file="MenuPatient.jsp"%> 
	
	<div class="container" style="float:left; margin-left:200px; ">
	
	<form:form name="creditcard-form" action="patientSubmitCC" method="post" class="registration-form" commandName="creditCard">
	
	       <div class="form-group">
				<label  class="col-sm-2 control-label">First Name</label>
					<div class="row"><div class="col-md-6">
					<form:input path="firstName" type="text" id="textbox" class="form-control" placeholder="First Name" required="true"/>
					<form:errors path="firstName" cssStyle="color:#ff0000"/></div>
					<div class="col-sm-4"></div>
					</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Last Name</label>
					<div class="row"><div class="col-md-4">
					<form:input path="lastName" type="text" id="textbox" class="form-control" placeholder="Last Name" required="true"/>
					<form:errors path="lastName" cssStyle="color:#ff0000"/></div></div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Number</label>
					<div class="row"><div class="col-md-4">
					<form:input path="number" type="text" id="textbox" class="form-control" placeholder="XXXX-XXXX-XXXX-XXXX" required="true"/>
					<form:errors path="number" cssStyle="color:#ff0000"/></div></div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Security Code</label>
					<div class="row"><div class="col-md-4">
					<form:input path="securityCode" type="text" id="textbox" class="form-control" placeholder="XXX" required="true"/>
					<form:errors path="securityCode" cssStyle="color:#ff0000"/></div></div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Expiry Date</label>
					<div class="row"><div class="col-md-4">
					<fmt:formatDate value="${existedEvent.expiryDate}" pattern="MM-dd-yyyy" var="formattedDate"/>
   					<form:input path="expiryDate" type="date" id="textbox" class="form-control" value = "${formattedDate}" required="true"/>(MM-dd-yyyy)
					<form:errors path="expiryDate" cssStyle="color:#ff0000"/></div></div>
			</div>
			<div class="form-group">
               <label for="type" class="col-sm-4 control-label">Card Type </label>
               <form:select path="type">
                   <form:option value="" label="" />
                   <form:options items="${cardTypes}" />
               </form:select>
               <form:errors path="type" cssStyle="color:#ff0000"></form:errors>
            </div>
            
            <div margin-left:400px; ">
              <input type="submit" value="Next" style="font-weight:bold"/><br/><br/>
           </div>
			
			<input type=hidden name=apptId value="${apptId}">
	</form:form>
	
	<div margin-left:400px; ">
	<form><input Type="button" VALUE="Back" onClick="history.go(-1);return true;"></form>
	</div>
	
	</div>
	
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%>
	
	
</body>
</html>