<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}" />
<fmt:bundle basename="localization.local" prefix="signup.">
	<fmt:message key="title" var="title" />
	<fmt:message key="main_header" var="main_header" />
	<fmt:message key="label_login" var="label_login" />
	<fmt:message key="placeholder_login" var="placeholder_login" />
	<fmt:message key="label_email" var="label_email" />
	<fmt:message key="placeholder_email" var="placeholder_email" />
	<fmt:message key="label_password" var="label_password" />
	<fmt:message key="placeholder_password" var="placeholder_password" />
	<fmt:message key="mobile" var="mobile" />
	<fmt:message key="placeholder_mobile" var="placeholder_mobile" />
	<fmt:message key="address" var="address" />
	<fmt:message key="placeholder_address" var="placeholder_address" />
	<fmt:message key="button_register" var="button_register" />
	<fmt:message key="link_login" var="link_login" />
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
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<title>${title}</title>
</head>

<body>

	<%@ include file="include/begin-html.jspf"%>

	<script type="text/javascript">
		function doAjaxLogin() {
			$.ajax({
				url : 'do?command=CheckSignup',
				data : ({
					nickname : $('#nickname').val()
				}),
				success : function(response) {
					$('#resultValueLogin').html(response);
				}
			})
		}

		function doAjaxEmail() {
			$.ajax({
				url : 'do?command=CheckSignup',
				data : ({
					email : $('#email').val()
				}),
				success : function(response) {
					$('#resultValueEmail').html(response);
				}
			})
		}

		function doAjaxPassword() {
			$.ajax({
				url : 'do?command=CheckSignup',
				data : ({
					password : $('#password').val()
				}),
				success : function(response) {
					$('#resultValuePass').html(response);
				}
			})
		}
	</script>

	<form class="form-horizontal" action="do?command=Signup" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>${main_header}</legend>


			<!-- Login input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nickname">${label_login}</label>
				<div class="col-md-4">
					<div class="form-group row">
						<input id="nickname" name="nickname" type="text"
							placeholder="TestLogin" class="form-control input-md" required
							onkeyup="doAjaxLogin()">
						<div id="resultValueLogin"></div>
					</div>
					<span class="help-block" id="resultValueLogin">${placeholder_login}</span>
				</div>
			</div>


			<!-- Email input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">${label_email}</label>
				<div class="col-md-4">
					<input id="email" name="email" type="text" placeholder=""
						class="form-control input-md" required
						value="TestEmail@google.com" onkeyup="doAjaxEmail()">
					<div id="resultValueEmail"></div>
					<span class="help-block">${placeholder_email}</span>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">${label_password}</label>
				<div class="col-md-4">
					<input id="password" name="password" type="password"
						placeholder="********" class="form-control input-md" required
						value="TestPassword" onclick="doAjaxPassword()">
					<div id="resultValuePass"></div>
					<span class="help-block">${placeholder_password}</span>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="mobile">${mobile}</label>
				<div class="col-md-4">
					<input id="mobile" name="mobile" type="text" placeholder=""
						class="form-control input-md" required value="+375295821155">
					<span class="help-block">${placeholder_mobile}</span>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="address">${address}</label>
				<div class="col-md-4">
					<input id="address" name="address" type="text" placeholder=""
						class="form-control input-md" required> <span
						class="help-block">${placeholder_address}</span>
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="btnSignup"></label>
				<div class="col-md-4">
					<button id="btnSignup" name="btnSignup" class="btn btn-success">${button_register}</button>
					<a href="do?command=Login">${link_login} <span class="sr-only">(current)</span></a>
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


