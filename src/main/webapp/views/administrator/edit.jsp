<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="administrator/edit.do" modelAttribute="user">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="numberOfMessages" />
	<form:hidden path="url" />
	<form:hidden path="comments" />
	<form:hidden path="name"/>
	<form:hidden path="surname"/>
	<form:hidden path="email"/>
	<form:hidden path="location"/>
	<form:hidden path="userAccount.username"/>
	<form:hidden path="userAccount.password"/>
	<form:hidden path="userAccount.authorities"/>
	<form:hidden path="ban"/>
	
	
	<input type="submit" name="save" value="<spring:message code="administrator.submit" />" />&nbsp;
	<input type="button" name="cancel" value="<spring:message code="administrator.user.cancel"/>" onclick="javascript: window.location.replace('administrator/list.do')"/>
	
</form:form>