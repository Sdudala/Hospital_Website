<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Login Page.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</head>

<body style="background-image: none;">
	
	  <c:choose>
  	  <c:when test="${sessionScope.user.role == 'ROLE_NURSE'}">
   			 <%@include file="MenuNurse.jsp"%> 
      </c:when>
      <c:when test="${sessionScope.user.role == 'ROLE_DOCTOR'}">
   			 <%@include file="MenuDoctor.jsp"%> 
      </c:when>
     </c:choose> 
	
	
	<div style="float:left; margin-left:200px; ">
    
   <%--  <form:form>	 --%>
	<table class="table table-hover table-bordered center">
			
		<tr>
			<td><b>Person ID</b></td>
			<td><b>First Name</b></td>
			<td><b>Last Name</b></td>
			<td><b>Contact</b></td>
			<td><b>DOB</b></td>
			<td><b>Gender</b></td>
			<td><b>Address</b></td>
			<td><b>Medical History</b></td>
		</tr>
	<c:forEach var="patient" items="${patientList}">
		<tr>
			<td>${patient.personId}</td>
			<td>${patient.firstName}</td>
			<td>${patient.lastName}</td>
			<td>${patient.contactNumber}</td>
			<td><fmt:formatDate value="${patient.dob}" type="both" pattern="MM-dd-yyyy" /></td>
			<td>${patient.gender}</td>
			<%-- <td><a href="<c:out value='viewAddress?personId=${patient.personId}'/>" >Address</a></td> --%>
			<td>
			<form:form action="viewAddress" method="post">
              <input type="Submit" value="Address"/>
              <input type=hidden name=personId value="${patient.personId}"/>
            </form:form>
            </td>
			
			<c:choose>
  	           <c:when test="${sessionScope.user.role == 'ROLE_NURSE'}">
   			      <!--  <td><a onclick="return noPrivilege()">Medical History</a></td> -->
   			      <td>
			         <form:form action="viewPatientHistory" method="post">
                     <input type="Submit" value="MedicalHistory" disabled/>
                     <input type=hidden name=personId value="${patient.personId}}">
                     </form:form>
                  </td>
               </c:when>
               <c:when test="${sessionScope.user.role == 'ROLE_DOCTOR'}">
   			      <%-- <td><a href="<c:out value='viewPatientHistory?personId=${patient.personId}'/>" >Medical History</a></td> --%>
   			      <td>
			         <form:form action="viewPatientHistory" method="post">
                     <input type="Submit" value="MedicalHistory"/>
                     <input type=hidden name=personId value="${patient.personId}">
                     </form:form>
                  </td>
               </c:when>
            </c:choose> 
		</tr>
  	</c:forEach>
  	</table>
   <%--  </form:form> --%>
	
	</div>
	
	<%@include file="Menu.jsp"%> 
	<%@include file="Footer.jsp"%> 
	
<script>
function noPrivilege()
{
	alert ("Nurse cannot access patient's medical history");
    return (false);
}

</script>
		
	
</body>
</html>