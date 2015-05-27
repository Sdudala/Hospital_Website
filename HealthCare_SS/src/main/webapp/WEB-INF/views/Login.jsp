<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Login Page.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
</head>

<body onload='document.loginForm.j_username.focus();'>
	<div class="navbar navbar-inverse navbar-static-top" id="stickyHeader">
				
		<div class="container" id="topbar">
			
			<div class="collapse navbar-collapse navHeaderCollapse">
			    <ul class="nav navbar-nav navbar-left">
			    <h4 style="color:white">Apollo Hospitals</h4>
			    </ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="#">Home</a></li>
				
					<li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Health Care Professionals<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Quality of Care</a></li>
							<li><a href="#">Provider Manuals</a></li>
						</ul>
					</li>
					
					<li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">Members<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Health Programs</a></li>
							<li><a href="#">Medicare Plans</a></li>
						</ul>
					</li>
									
				</ul>
			</div>
			
		</div>
	</div>
	
	<div class="clear"></div>
	
	<div class="row">	
	
		<div class="col-sm-6" id="accountLogin">
				Account Login<br/><br/>
			   <%-- <form:form action="checkLogin" method="post" commandName="userAccount">
					<form:input path="userName"  type="text" placeholder="Enter username" value ="${userName}" />&nbsp;&nbsp;<form:errors path="userName" cssStyle="color:#ff0000"></form:errors><br/><br/>
					<form:input path="password" type="password" placeholder="Enter password" value="${password}"/>&nbsp;&nbsp;<form:errors path="password" cssStyle="color:#ff0000"></form:errors><br/><br/>
					<input type="submit" value="Login" style="font-weight:bold"/>
			    	<label><input type="checkbox" name="remember">Remember me</label><br/><br/>
				</form:form>  --%>
				
				<c:if test="${'fail' eq param.auth}">
				<div style="color:red">
				Login Failed!!!<br/>
				Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}<br/><br/>
				</div>
				</c:if>
				
				
               <form name='loginForm' action='<c:url value='/j_spring_security_check' />' method='POST'>		
               
               <div class="form-group">
				<label  class="col-sm-2 control-label">Username:</label>
					<div class="row"><div class="col-md-6">
					<input type='text' name='j_username' value=''></div></div>
		       </div>
		       <div class="form-group">
				<label  class="col-sm-2 control-label">Password:</label>
					<div class="row"><div class="col-md-6">
					<input type='password' name='j_password' value=''></div></div>
		       </div>
			
		    	<div class="form-group"> 
		        	<label  class="col-sm-2 control-label"></label>
					<div class="row"><div class="col-md-6">
			 		<input type="submit" value="Login" style="font-weight:bold"/></div></div>   
				</div>
				<!-- <div class="form-group"> 
					<label  class="col-sm-2 control-label"></label>
					<div class="row"><div class="col-md-6">
					<input type="checkbox" name="remember" value="remember">Remember me</div></div>
		    	</div> -->
       		  </form>
				
			
		</div>	
	</div>
	<br /><br />
	<div class="row">
		<div class="col-sm-6">
			New User??<a href="registration">Register Here</a>
		</div>
	</div>
	
	<br/><br/>
	
	<%@include file="Footer.jsp"%> 	
	
	
</body>
</html>