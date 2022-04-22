<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FIFA 2022</title>
<spring:url value="/css/style.css" var="urlCss" />
<link rel="STYLESHEET" href="${urlCss}" type="text/css" />
</head>
<body>
	<c:if test="${uitverkocht}">
		<p>
			<spring:message code="uitverkocht.message" />
		</p>
	</c:if>
	<c:if test="${verkocht != 0}">
		<p>${verkocht}
			<spring:message code="tickets.message" />
		</p>
	</c:if>
	<h1 class="formh1">FIFA World Cup Qatar 2022</h1>
	<form:form method="POST" action="fifa" modelAttribute="fifaCommand">
		<p>
			<spring:message code="stadium.message" />
			<form:select path="selectedStadium" multiple="false">
				<form:options items="${stadiumList}" />
			</form:select>
		</p>
		<input class="butt" type="submit"
			value="<spring:message code="formButton.message"/>">
	</form:form>
</body>
</html>