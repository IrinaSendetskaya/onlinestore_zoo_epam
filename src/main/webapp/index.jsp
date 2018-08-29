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
<link rel="stylesheet"
	href="<c:url value="resources/css/bootstrap.min.css"/>">

<title>Интернет-магазин</title>
</head>

<body>

	<%@ include file="WEB-INF/views/include/begin-html.jspf"%>

	<div class="page-header">
		<h1>Tовары</h1>
		<p class="lead"></p>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-3">Товар</div>
			<div class="col-md-4">Изображение</div>
			<div class="col-md-1">Цена</div>
			<div class="col-md-1">ед.изм.</div>
			<div class="col-md-1"></div>
		</div>
		<br>

		<c:forEach items="${goodsListForJsp}" var="good">
			<form class="update-good-${good.id}" action="do?command=Index"
				method=POST>
				<div class="row">

					<div class="col-md-1">
						<input id="id" class="form-control input-md" name="id"
							type="hidden" class="form-control" value="${good.id}" />
					</div>
					<div class="col-md-3" id="name" accesskey="name">${good.name}</div>
					<div class="col-md-4">
						<img src="${good.imageUrl}" width="150" height="150" />
					</div>
					<div class="col-md-1">${good.price}</div>
					<div class="col-md-1">${good.size}</div>

					<c:if test="${buyer.roleId!=3}">
						<div class="col-md-1">
							<button id="btnInBasket" value="btnInBasket" name="btnInBasket"
								class="btn btn-success">В корзину</button>
						</div>
					</c:if>
				</div>
				<br> 
			</form>
		</c:forEach>
	</div>

	<hr>
	<div class="row">
		<mytag:paginator count="${goodsSize}" step="5" urlprefix="?start=" />
	</div>
	<hr>

	<br>
	<br>


	<%@ include file="WEB-INF/views/include/end-html.jspf"%>

</body>
</html>