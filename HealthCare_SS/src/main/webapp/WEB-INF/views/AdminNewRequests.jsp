<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pending User Requests</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Login Page.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<script src="${pageContext.request.contextPath}/resources/css/jquery-1.10.2.js"></script>
</head>

<body style="background-image: none;">

	<%@include file="MenuAdmin.jsp"%> 

    <div style="float:left; margin-left:200px; ">

	<form action="adminUpdateUsers" method="post" id="Myform">	
	<table class="table table-hover table-bordered center">
			
		<tr>
			<td><b>UserName</b></td>
			<td><b>First Name</b></td>
			<td><b>Last Name</b></td>
			<td><b>Role</b></td>
			<td><b></b></td>
		</tr>
	<c:forEach var="ua" items="${uaList}">
		<tr>
			<td>${ua.userName}</td>
			<td>${ua.person.firstName}</td>
			<td>${ua.person.lastName}</td>
			<td>${ua.role}</td>
			<td><input type="checkbox" name="userId" value="${ua.userId}"></td>
		</tr>
  	</c:forEach>
  	</table>
	
	
	<input type="submit" name="submit" value="Accept" style="float:none" /> &nbsp; &nbsp; &nbsp;
    <input type="submit" name="submit" value="Reject"/>
    </form>
	
	</div>

	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%>
	
<script>
	function onSubmit() 
    { 
	    var fields = $("input[name='userId']").serializeArray(); 
        if (fields.length == 0) 
        { 
           alert('nothing selected'); 
           // cancel submit
          return false;
        } 
        else 
        { 
           alert(fields.length + " selected"); 
        }
     }

// register event on form, not submit button
$('#Myform').submit(onSubmit)
</script>
	
</body>
</html>