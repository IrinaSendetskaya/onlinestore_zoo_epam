<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<title>Интернет-магазин</title>
</head>

<body>

	<%@ include file="include/begin-html.jspf"%>



	<form class="form-horizontal" action="do?command=Login" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>Login page</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nickname">Login</label>
				<div class="col-md-4">
					<input id="nickname" name="nickname" type="text"
						placeholder="input login" class="form-control input-md"
						value="TestLogin"> <span class="help-block">your
						login</span>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Password</label>
				<div class="col-md-4">
					<input id="password" name="password" type="password"
						placeholder="input password" class="form-control input-md"
						value="TestPassword"> <span class="help-block">(min
						4 symbols)</span>
				</div>
			</div>

			<!-- Button (Double) -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="btnLoginOkID"></label>
				<div class="col-md-8">
					<button id="btnLoginOkID" name="btnLoginOkID"
						class="btn btn-success">Войти</button>
					<!-- <button id="btnLoginCancelID" name="btnLoginCancelID"
						class="btn btn-danger">Отмена</button> -->
				</div>
			</div>

		</fieldset>
	</form>

	<p>
		Cmd Login: <b>${message}</b>
	</p>


	<%@ include file="include/end-html.jspf"%>

</body>
</html>