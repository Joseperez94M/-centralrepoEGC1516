<%--
 * list.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table name="comments" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag" keepStatus="true">

	<spring:message code="comment.thread" var="thread" />
	<display:column property="thread.title" title="${thread}" sortable="true" />

	<spring:message code="comment.text" var="text" />
	<display:column property="text" title="${text}" sortable="true" />
	
	<spring:message code="comment.creationMoment" var="creationMoment" />
	<display:column property="creationMoment" title="${creationMoment}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<display:column>
		<a href="comment/customer/edit.do?commentId=${row.id}">
			<spring:message code="comment.edit"/>
		</a>
	</display:column>
		
</display:table>
