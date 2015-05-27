<#--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<#--
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Login Page.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
-->
</head>

<body style="background-image: none;">
    
	<div style="float:left; width:200px; ">
		<ul class="nav nav-pills nav-justified">
			<form action="nurse" method="post">
                 <input type="Submit" value="Home" class="btn btn-default btn-block" />
            </form>
		</ul>
		
		<ul class="nav nav-pills nav-justified">
			<form action="nurseAppointments" method="post">
                 <input type="Submit" value="Manage Appointments" class="btn btn-default btn-block" />
            </form>
		</ul>
		
		<ul class="nav nav-pills nav-justified">
			<form action="nurseSearch" method="post">
                 <input type="Submit" value="Search Patient" class="btn btn-default btn-block" />
            </form>
		</ul>
		
		<ul class="nav nav-pills nav-justified">
			<form action="changePassword" method="post">
                 <input type="Submit" value="Change Password" class="btn btn-default btn-block" />
            </form>
		</ul>
		
		<ul class="nav nav-pills nav-justified">
			<form action="logout" method="post">
                 <input type="Submit" value="Logout" class="btn btn-default btn-block" />
            </form>
		</ul>
	</div>
	
	<div style="float:left">
		<h1 id="h1">Appointment sent to Doctor</h1>
	</div>
	<div class="clear"></div>
	
	<div class="navbar navbar-inverse navbar-static-top" id="stickyHeader">
				
		<div class="container" id="topbar">
			
			<div class="collapse navbar-collapse navHeaderCollapse">
			    <ul class="nav navbar-nav navbar-left">
			    <h4 style="color:white">Apollo Hospitals</h1>
			    </ul>
			</div>
			
		</div>
	</div>
	
	 <footer>
      <div class="container">
        <p > 
			<span style="margin-left:20px;">© 2015 Apollo Hospitals, Inc. All Rights Reserved</span>
		</p>
      </div>
    </footer>

	
</body>
</html>