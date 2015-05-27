<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
     
     <div style="float:left; margin-left:100px; ">
     
     <form:form action="confirmPasswordChange" method="post" commandName="userAccount">
          <div class="form-group">
				<!-- <label for="oldPassword" class="col-sm-4 control-label">OldPassword</label> -->
			    <div class="row">
			       <div class="col-md-4">OldPassword</div>
			       <div class="col-md-4"><input type="password" name="oldPassword"></div>
			    </div>
		  </div>
 		  <div class="form-group">
				<label for="inputPassword" class="col-sm-4 control-label">Password</label>
			    <div class="row"><div class="col-md-6">
			    <form:input path="password" type="password" id="textbox" class="form-control" size="35"/>
				<form:errors path="password" cssStyle="color:#ff0000"/></div></div>
		  </div>
			  
		  <div class="form-group">
				<label for="confirmInputPassword" class="col-sm-4 control-label">Confirm Password</label>
		        <div class="row"><div class="col-md-6">
				<form:input path="confirmPassword" type="password" id="textbox" class="form-control" size="35"/>
				<form:errors path="confirmPassword" cssStyle="color:#ff0000"/></div></div>
		  </div>
		  
		   <!-- <a type="button" href="Login Page.html" class="btn btn-default">Cancel</a> -->
		   <input type="submit" value="Submit" class="btn btn-success btn-lg" style="font-weight:bold"/>
     </form:form>
    </div>
	
		
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
	
	
</body>
</html>