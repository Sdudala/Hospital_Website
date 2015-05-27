<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Registration Form</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Login Page.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	
</head>

<body style="background-image: none">

<style type="text/css">
label {display: inline;}
</style>

	 
    <div class="container" style="margin-left:60px">
    	
    	<form:form action="login">
              <input type="Submit" value="Go Back" class="btn btn-warning" />
        </form:form>
    	
      
         <h1 id="h1" style="margin-top: -10px;font-size:35px;margin-left:-30px">Member Registration</h1>
         <h5 style="color:red;">*All fields are required</h5><br/>
         
         <c:choose>
  	       <c:when test="${userExists == '1'}">
   			 <h5 style="color:red;">*User already exists</h5><br/>
           </c:when>
         </c:choose> 
         
         
		 <form:form name="registration-form" action="submitForm" method="post" class="registration-form" commandName="userAccount">
            
            <h3>Personal Information</h3>
			
			<div class="form-group">
				<label  class="col-sm-2 control-label">First Name</label>
					<div class="row"><div class="col-md-6">
					<form:input path="person.firstName" type="text" id="textbox" class="form-control" placeholder="First Name" required="true"/>
					<form:errors path="person.firstName" cssStyle="color:#ff0000"/></div>
					<div class="col-sm-4"></div>
					</div>
					
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Last Name</label>
					<div class="row"><div class="col-md-4">
					<form:input path="person.lastName" type="text" id="textbox" class="form-control" placeholder="Last Name" required="true"/>
					<form:errors path="person.lastName" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
               <label for="person.gender" class="col-sm-4 control-label">Gender </label>
               <form:select path="person.gender">
                   <form:option value="" label="" />
                   <form:options items="${genders}" />
               </form:select>
               <form:errors path="person.gender" cssStyle="color:#ff0000"></form:errors>
            </div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Mobile</label>
					<div class="row"><div class="col-md-4">
					<form:input path="person.contactNumber" type="text" id="textbox" class="form-control" placeholder="XXX-XXX-XXXX" required="true"/>
					<form:errors path="person.contactNumber" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Date of Birth</label>
					<div class="row"><div class="col-md-4">
					<fmt:formatDate value="${existedEvent.person.dob}" pattern="MM/dd/yyyy" var="formattedDate"/>
   					<%-- <form:input path="person.dob" type="date" id="textbox" class="form-control" value = "${formattedDate}" required="true"/>(MM-dd-yyyy) --%>
   					<form:input path="person.dob" type="date" id="textbox" class="form-control" required="true"/>(MM-dd-yyyy)
					<form:errors path="person.dob" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Street</label>
					<div class="row"><div class="col-md-4">
					<form:input path="person.address.street" type="text" id="textbox" class="form-control" required="true"/>
					<form:errors path="person.address.street" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">City</label>
					<div class="row"><div class="col-md-4">
					<form:input path="person.address.city" type="text" id="textbox" class="form-control"  required="true"/>
					<form:errors path="person.address.city" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			
			<div class="form-group">
				<label class="col-sm-2 control-label">State</label>
					<div class="row"><div class="col-md-4">
					<form:input path="person.address.state" type="text" id="textbox" class="form-control"  required="true"/>
					<form:errors path="person.address.state" cssStyle="color:#ff0000"/></div></div>
			</div>
            
            <div class="form-group">
				<label class="col-sm-2 control-label">Country</label>
					<div class="row"><div class="col-md-4">
					<form:input path="person.address.country" type="text" id="textbox" class="form-control"  required="true"/>
					<form:errors path="person.address.country" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Zip Code</label>
					<div class="row"><div class="col-md-4">
					<form:input path="person.address.zipcode" type="text" id="textbox" class="form-control" required="true"/>
					<form:errors path="person.address.zipcode" cssStyle="color:#ff0000"/></div></div>
			</div>
			
			<hr id="hr">
			
						
            <h3>Create Login</h3>
            
            <div class="form-group">
               <label for="type" class="col-sm-4 control-label">Account Type </label>
               <form:select path="role">
                   <form:option value="" label="" />
                   <form:options items="${roles}" />
               </form:select>
               <form:errors path="role" cssStyle="color:#ff0000"></form:errors>
            </div>
			
			<div class="form-group">
			<div class="row">
				<label for="inputUserName" class="col-sm-2 control-label">Username</label>
				<div class="col-md-4">
				<form:input path="userName" type="text" id="textbox" class="form-control" placeholder="Username" required="true"/>&nbsp;&nbsp;
				<form:errors path="userName" cssStyle="color:#ff0000"/>
				</div>
				<div class="col-md-6"></div>
				</div>
			</div>
			
			  <div class="form-group">
				<label for="inputPassword" class="col-sm-2 control-label">Password</label>
				  <div class="row"><div class="col-md-4">
				  <form:input path="password" type="password" id="textbox" class="form-control" placeholder="Password" required="true"/>
				  <form:errors path="password" cssStyle="color:#ff0000"/></div></div>
			  </div>
			  
			  <div class="form-group">
				<label for="confirmInputPassword" class="col-sm-4 control-label">Confirm Password</label>
				  <div class="row"><div class="col-md-4">
				  <form:input path="confirmPassword" type="password" id="textbox" class="form-control" placeholder="Password" required="true"/>
				  <form:errors path="confirmPassword" cssStyle="color:#ff0000"/></div></div>
			  </div>
            
            <!-- <a type="button" href="Login Page.html" class="btn btn-default">Cancel</a> -->
			<input type="submit" value="Submit" class="btn btn-success btn-lg" style="font-weight:bold"/>
			
        </form:form>

     </div>
     <br/><br/></br><br/>
	
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
	
    </body>
</html>