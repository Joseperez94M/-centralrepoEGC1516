<%--
 * editThread.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<form:form action="administrator/editThread.do" modelAttribute="thread">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="administrator"/>
	<form:hidden path="creationMoment" />
	<form:hidden path="comments" />

	<acme:textbox code="administrator.deliberations.title" path="title" />
	<acme:textbox code="administrator.deliberations.text" path="text" />
	<acme:textarea code="administrator.deliberations.censudId"
		path="censusId" />

	<input type="submit" name="save"
		value="<spring:message code="administrator.save"/>" />
	&nbsp
	<input type="button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onclick="javascript: window.location.replace('customer/listThreads.do);" />

<jstl:if test="${thread.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="administrator.thread.delete"/>"
			onclick="return confirm('<spring:message code="administrator.thread.confirm.delete" />')" />
		&nbsp
	</jstl:if>
</form:form>