<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="users" id="user" pagesize="5" requestURI="${requestURI}" class="displaytag">
	<display:column>
		<security:authorize access="hasRole('ADMIN')">
			<a href="administrator/edit.do?userId=${user.id}">
				<spring:message code="administrator.user.edit" />
			</a>
		</security:authorize>
	</display:column>
	<spring:message code="user.name" var="varname" />
	<display:column title="${varname}" property="name" />
	
	<spring:message code="user.surname" var="varsurname" />
	<display:column title="${varsurname}" property="surname" />
	
	<spring:message code="user.email" var="varemail" />
	<display:column title="${varemail}" property="email" />
	
	<spring:message code="user.account" var="varaccount" />
	<display:column title="${varaccount}" property="userAccount.username" />
	
	<spring:message code="user.banned" var="varbanned" />
	<display:column title="${varbanned}" property="ban.banned" />

</display:table>