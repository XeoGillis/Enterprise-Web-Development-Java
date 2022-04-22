<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${fifaCommand.selectedStadium}</title>
<spring:url value="/css/style.css" var="urlCss" />
<link rel="STYLESHEET" href="${urlCss}" type="text/css" />
</head>
<body>
	<h1>FIFA World Cup Qatar 2022</h1>
	<h2>
		<spring:message code="chosenStadium.message" />
		${fifaCommand.selectedStadium}
	</h2>
	<table class="zigzag">
		<thead>
			<tr>
				<th class="header"><spring:message code="nr.message" /></th>
				<th class="header"><spring:message code="match.message" /></th>
				<th class="header"><spring:message code="datum.message" /></th>
				<th class="header"><spring:message code="aftrap.message" /></th>
				<th class="header"><spring:message code="availableTickets.message" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="match" items="${matchList}">
				<tr>
					<td><form:form method="POST"
							action="fifa/${match.wedstrijd.id}" modelAttribute="fifaCommand">
							<form:input path="selectedMatch" value="${match.wedstrijd.id}"
								type="hidden" />
							<input type="submit" value="${match.wedstrijd.id}">
						</form:form></td>
					<td>${match.wedstrijd.landen[0]}-${match.wedstrijd.landen[1]}</td>
					<td>${match.wedstrijd.dag}</td>
					<td>${match.wedstrijd.uur}</td>
					<td>${match.tickets}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>