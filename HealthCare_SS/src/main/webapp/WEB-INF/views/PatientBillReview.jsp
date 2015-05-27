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
			<td><b>Consultation Fee: </b></td>
			<td><b>50</b></td>
		    </tr>
	        <c:forEach var="medOrder" items="${appointment.medicationList}">
	    	<tr>
			<td>${medOrder.drug}(x${medOrder.dosage})</td>
			<td>${medOrder.dosage}</td>
		    </tr>
		    </c:forEach>
		    <tr>
			<td><b>Total Fee: </b></td>
			<td><b>${appointment.totalBill}</b></td>
		    </tr>
  	    	</table>
        </form:form> 
        
        <form:form name="Myform" action="patientPaymentAction" method="post">
        <c:choose>
    	  <c:when test="${creditCardPresent == '1'}">
    	      <input type="radio" name="options" value="existing" checked>Use credit card on file<br>
              <input type="radio" name="options" value="new">Use new card<br/></br>      
          </c:when>
          <c:when test="${creditCardPresent == '0'}">
             <input type="radio" name="options" value="new" checked>Use new card<br>   
          </c:when>
        </c:choose> 
        <input type="submit" value="Next"><br/><br/>
        
       	<input type=hidden name=apptId value="${apptId}">
        </form:form>
                
        <form><input Type="button" VALUE="Back" onClick="history.go(-1);return true;"></form>
        
    </div>
		
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
</body>
</html>
