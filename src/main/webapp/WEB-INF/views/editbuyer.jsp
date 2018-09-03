<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"%>

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

<title>Интернет-магазин</title>
</head>

<body>

	<%@ include file="include/begin-html.jspf"%>

	<script type="text/javascript">
		function doAjaxSignup() {
			$.ajax({
				url : 'signup',
				data : ({
					password : $('#pass').val(),
					nickname : $('#name').val()
				}),
				success : function(data) {
					$('#resultValue').html(data);
				}
			})
		}
	</script>

	<div class="container">
		<div class="row">
			<div class=col-md-1></div>
			<div class=col-md-1>Имя</div>
			<div class=col-md-1>Пароль</div>
			<div class=col-md-2>Email</div>
			<div class=col-md-1>Mobile</div>
			<div class=col-md-2>Address</div>
			<div class=col-md-2>Роль</div>
		</div>
	</div>

	<div class="container">
		<c:forEach items="${buyers}" var="buyer">
			<form class="update-user-${buyer.id}" action="do?command=EditBuyer"
				method=POST>
				<div class="row">
					<div class=col-md-1>
						<input id="id" class="form-control input-md" name="id"
							type="hidden" value="${buyer.id}" />
					</div>
					<div class=col-md-1>
						<input id="nickname" class="form-control input-md" name="nickname"
							value="${buyer.nickname}" />
					</div>
					<div class=col-md-1>
						<input id="password" class="form-control input-md" name="password"
							type="hidden" value="${buyer.password}" />
					</div>
					<div class=col-md-2>
						<input id="email" class="form-control input-md" name="email"
							value="${buyer.email}" />
					</div>
					<div class=col-md-1>
						<input id="mobile" class="form-control input-md" name="mobile"
							value="${buyer.mobile}" />
					</div>
					<div class=col-md-2>
						<input id="address" class="form-control input-md" name="address"
							value="${buyer.address}" />
					</div>

					<div class=col-md-2>
						<select id="role" name="fk_roles" class="form-control">
							<c:forEach items="${roles}" var="role">
								<option value="${role.id}" role=${role.id
									}
									${role.id==buyer.roleId?"selected":""}>${role.role}</option>
							</c:forEach>
						</select>
					</div>

					<div class=col-md-1>
						<button id="btnUpdateBuyer" value="btnUpdateBuyer"
							name="btnUpdateBuyer" class="btn btn-success">Обновить</button>
					</div>

					<div class=col-md-1>
						<button id="btnDeleteBuyer" value="btnDeleteBuyer"
							name="btnDeleteBuyer" class="btn btn-danger" onclick="doAjax()">
							Удалить</button>
					</div>
				</div>
			</form>
		</c:forEach>
	</div>


	<hr>

	<%-- <div class="row">
		<mytag:paginator count="${goodsSize}" step="5"
			urlprefix="do?command=Profile&start=" />
	</div> --%>

	<hr>

	<br>
	<br>
	<%@ include file="include/end-html.jspf"%>

</body>
</html>


