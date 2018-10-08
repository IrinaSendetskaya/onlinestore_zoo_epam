<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${currentLocale}" />
<fmt:bundle basename="localization.local" prefix="login.">
	<fmt:message key="title" var="title" />
	<fmt:message key="main_header" var="main_header" />
	<fmt:message key="label_login" var="label_login" />
	<fmt:message key="placeholder_login" var="placeholder_login" />
	<fmt:message key="label_password" var="label_password" />
	<fmt:message key="placeholder_password" var="placeholder_password" />
	<fmt:message key="button_login" var="button_login" />
	<fmt:message key="link_register" var="link_register" />
	<fmt:message key="loginError" var="loginError" />
	<fmt:message key="loginDuplication" var="loginDuplication" />
	<fmt:message key="msg" var="msg" />
</fmt:bundle>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<!-- <script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script> -->
<!-- <script src="resources/js/jquery.validate.js"></script> -->
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js" type="text/javascript"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="resources/js/login.validate.js"></script>
<title>${title}</title>
</head>

<body>

	<%@ include file="include/begin-html.jspf"%>



	<form id="formLogin" class="form-horizontal" action="do?command=Login"
		method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>${main_header}</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nickname">${label_login}</label>
				<div class="col-md-4">
					<input id="nickname" name="nickname" type="text"
						placeholder="${placeholder_login}" class="form-control input-md"
						value="TestLogin"> <span class="help-block">
						${placeholder_login}</span>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">${label_password}</label>
				<div class="col-md-4">
					<input id="password" name="password" type="password"
						placeholder="${placeholder_password}"
						class="form-control input-md" value="TestPassword"> <span
						class="help-block"> ${placeholder_password}</span>
				</div>
			</div>

			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="btnLoginOkID"></label>
				<div class="col-md-4">
					<button id="btnLoginOkID" name="btnLoginOkID" type="submit"
						class="btn btn-outline-primary">${button_login}</button>

					<a href="do?command=SignUp">${link_register} <span
						class="sr-only">(current)</span></a>
				</div>
			</div>

		</fieldset>
	</form>
	<c:if test="${not empty requestScope.loginError}">
		<div class="alert alert-danger" role="alert">
			<c:out value="${loginError}"></c:out>
		</div>
	</c:if>
	<c:if test="${not empty requestScope.loginDuplication}">
		<div class="alert alert-danger" role="alert">
			<c:out value="${loginDuplication}"></c:out>
		</div>
	</c:if> 
	<p>
		${msg} <b>${message}</b>
	</p>


	<%@ include file="include/end-html.jspf"%>

</body>
</html>