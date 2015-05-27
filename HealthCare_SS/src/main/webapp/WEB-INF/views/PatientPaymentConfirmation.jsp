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
    
   		<form:form>	 
        	<table class="table table-hover table-bordered center">
	       	<tr>
			<td><b>Total Fee: </b></td>
			<td><b>${bill}</b></td>
		    </tr>
		   	<tr>
			<td><b>Credit Card: </b></td>
			<td><b>XXXX-XXXX-XXXX-${creditCardNumber}</b></td>
		    </tr>
  	    	</table>
        </form:form> 
                       
        <form><input Type="button" VALUE="Back" onClick="history.go(-1);return true;"><br/><br/></form>
         <form:form action="patientPaymentSuccessful">
              <input type="Submit" value="Confirm Payment" class="btn btn-warning" />
              <input type=hidden name=apptId value="${apptId}">
        </form:form>
        
    </div>
		
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
</body>
</html>
