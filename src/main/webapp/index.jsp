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
<link rel="stylesheet"
	href="<c:url value="resources/css/bootstrap.min.css"/>">

<title>Интернет-магазин</title>
</head>

<body>

	<%@ include file="WEB-INF/views/include/begin-html.jspf"%>

	<div class="page-header">
		<h1>Все товары</h1>
		<p class="lead"></p>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-1">Цена</div>
			<div class="col-md-1">ед.изм.</div>
			<div class="col-md-1"></div>
			<div class="col-md-2">Товар</div>
			<div class="col-md-1">Раздел</div>
			<div class="col-md-3">Описание</div>
			<div class="col-md-3">Изображение</div>
			<div class="col-md-1"></div>

		</div>
		<br>

		<c:forEach items="${goods}" var="good">
			<form class="update-good-${good.id}" action="do?command=Index"
				method=POST>
				<div class="row">

					<div class="col-md-1">
						<input id="id" class="form-control input-md" name="id"
							type="hidden" class="form-control" value="${good.id}" />
					</div>
					<div class="col-md-1">
						<input id="price" class="form-control input-md" name="price"
							class="form-control" value="${good.price}" />
					</div>
					<div class="col-md-1">${good.measureId}</div>

					<div class="col-md-1">
						<input id="id" class="form-control input-md" name="id"
							type="hidden" class="form-control"
							value="${good.specificationGoodId}" />
					</div>

					<c:forEach items="${specificationsGoods}" var="specificationGoods">
						<%-- <form class="update-good-${specificationGoods.id}" action="do?command=Index"
							method=POST> --%>
						<div class="row">

							<div class="col-md-2">
								<input id="price" class="form-control input-md" name="price"
									class="form-control" value="${specificationGoods.name}" />
							</div>
							<div class="col-md-1">${specificationGoods.sectionId}</div>
							<div class="col-md-3">${specificationGoods.description}</div>

							<div class="col-md-1">
								<input id="id" class="form-control input-md" name="id"
									type="hidden" class="form-control"
									value="${specificationGoods.imageId}" />
							</div>

							<c:forEach items="${images}" var="image">
								<div class="row">
									<div class="col-md-2">${image.imageUrl}</div>
								</div>
							</c:forEach>

						</div>

						<!-- </form> -->

					</c:forEach>

					<c:if test="${buyer.roleId!=3}">
						<div class="col-md-1">
							<button id="btnInBasket" value="btnInBasket" name="btnInBasket"
								class="btn btn-success">В корзину</button>
						</div>
					</c:if>
				</div>

			</form>

		</c:forEach>
	</div>

	<br>
	<br>


	<%@ include file="WEB-INF/views/include/end-html.jspf"%>

</body>
</html>