<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<spring:url value="/css/style.css" var="urlCss" />
<link rel="STYLESHEET" href="${urlCss}" type="text/css" />
<spring:url value="/img/Club.gif" var="urlImg" />
</head>
<body>
	<h1>FIFA World Cup Qatar 2022</h1>
	<h2>
		<spring:message code="chosenStadium.message" />
		${stadium}
	</h2>

	<h3>${match.wedstrijd}</h3>
	<h3>
		<spring:message code="ticketsAvailable.message" />
		${match.tickets}
	</h3>

	<form:form method="POST" action="check" modelAttribute="fifaCommand">
		
		<label><spring:message code="email.message" /></label>
		<form:input path="email" />
		<br />
		<form:errors path="email" cssClass="error" />
		<br />
		
		<label><spring:message code="ticket.message" /></label>
		<form:input path="ticket" value="${match.DEFAULT_TICKETS }" />
		<br />
		<form:errors path="ticket" cssClass="error" />
		<br />
		
		<label><spring:message code="voetbalCode1.message" /></label>
		<form:input path="voetbalCode1" value="${match.DEFAULT_CODE1}" />
		<br />
		<form:errors path="voetbalCode1" cssClass="error" />
		<br />
		
		<label><spring:message code="voetbalCode2.message" /></label>
		<form:input path="voetbalCode2" value="${match.DEFAULT_CODE2}" />
		<br />
		<form:errors path="voetbalCode2" cssClass="error" />
		<br />
		<input class="butt" type="submit"
			value="<spring:message code="koop" />">
	</form:form>

</body>
</html>