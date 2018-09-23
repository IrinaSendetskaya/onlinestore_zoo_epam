<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentLocale}"/>	
<fmt:bundle basename="localization.local" prefix = "login.">
	<fmt:message key="title" var="title"/>
	<fmt:message key="main_header" var="main_header"/>
	<fmt:message key="label_login" var="label_login"/>
	<fmt:message key="placeholder_login" var="placeholder_login"/>
	<fmt:message key="label_password" var="label_password"/>
	<fmt:message key="placeholder_password" var="placeholder_password"/>
	<fmt:message key="button_login" var="button_login"/>
	<fmt:message key="link_register" var="link_register"/>
	<fmt:message key="msg" var="msg"/>
</fmt:bundle>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<title>${title}</title>
</head>

<body>

	<%@ include file="include/begin-html.jspf"%>



	<form class="form-horizontal" action="do?command=Login" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>${main_header}</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nickname">${label_login}</label>
				<div class="col-md-4">
					<input id="nickname" name="nickname" type="text"
						placeholder="input login" class="form-control input-md"
						value="TestLogin"> <span class="help-block">
						${placeholder_login}</span>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">${label_password}</label>
				<div class="col-md-4">
					<input id="password" name="password" type="password"
						placeholder="input password" class="form-control input-md"
						value="TestPassword"> <span class="help-block">
						${placeholder_password}</span>
				</div>
			</div>

			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="btnLoginOkID"></label>
				<div class="col-md-12">
					<button id="btnLoginOkID" name="btnLoginOkID"
						class="btn btn-success">${button_login}</button>
		
					<a href="do?command=SignUp">${link_register} <span
								class="sr-only">(current)</span></a>
				</div>
			</div>

		</fieldset>
	</form>

	<p>
		${msg} <b>${message}</b>
	</p>


	<%@ include file="include/end-html.jspf"%>

</body>
</html>