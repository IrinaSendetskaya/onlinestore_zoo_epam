<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF8"%>

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


	<form class="form-horizontal" action="do?command=Signup" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>Регистрация нового пользователя</legend>


			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nickname">Логин</label>
				<div class="col-md-4">
					<input id="nickname" name="nickname" type="text" placeholder=""
						class="form-control input-md" required="" value="TestLogin">
					<span class="help-block">Введите Ваш логин</span>
				</div>
			</div>



			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">E-mail</label>
				<div class="col-md-4">
					<input id="email" name="email" type="text" placeholder=""
						class="form-control input-md" required=""
						value="TestEmail@google.com"> <span class="help-block">Укажите
						e-mail</span>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Пароль</label>
				<div class="col-md-4">
					<input id="password" name="password" type="password"
						placeholder="********" class="form-control input-md" required=""
						value="TestPassword"> <span class="help-block">Введите
						пароль минимум 4 символов</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-4 control-label" for="mobile">Телефон для связи</label>
				<div class="col-md-4">
					<input id="mobile" name="mobile" type="text" placeholder=""
						class="form-control input-md" required=""
						value="+375295821155"> <span class="help-block">Укажите
						телефон для связи</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-4 control-label" for="address">Адрес доставки</label>
				<div class="col-md-4">
					<input id="address" name="address" type="text" placeholder=""
						class="form-control input-md" required=""
						> <span class="help-block">Укажите
						адрес доставки</span>
				</div>
			</div>
			

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="btnSignup"></label>
				<div class="col-md-4">
					<button id="btnSignup" name="btnSignup" class="btn btn-success">Зарегистрироваться</button>
				</div>
			</div>

		</fieldset>
	</form>

	<p>
		SIGN-UP MESSAGE: <b>${message}</b>
	</p>


	<%@ include file="include/end-html.jspf"%>

</body>
</html>


