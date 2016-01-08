<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<form:form action="customer/saveCabbage.do" method="post" modelAttribute="cabbage">

<form:hidden path="id"/>
<form:hidden path="version"/>	
<form:hidden path="customer"/>


<acme:textbox code="customer.cabbage.title" path="title"/>
<acme:textbox code="customer.cabbage.description" path="description"/>
<acme:textbox code="customer.cabbage.url" path="url"/>

<acme:submit name="save" code="customer.submit"/>





</form:form>