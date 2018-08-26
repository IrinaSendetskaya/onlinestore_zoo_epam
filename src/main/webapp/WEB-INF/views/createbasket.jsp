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


	<fieldset>

		<!-- Form Name -->
		<legend>Создать корзину</legend>

		<div class="container">
			<div class="page-header">
				<h1>Корзина</h1>
				<p class="lead"></p>
			</div>

		</div>

		<div class="container">
			<c:forEach items="${baskets}" var="basket">
				<form class="update-user-${basket.id}"
					action="do?command=CreateBasket" method=POST>
					<div class="row">
						<div class="col-md-2">№ заказа</div>
						<div class="col-md-1">Количество</div>
						<div class="col-md-2">Сумма</div>
						<div class="col-md-2">№ покупателя</div>


					</div>
					<br>


					<div class="row">
						<div class=col-md-2>
							<input id="id" class="form-control input-md" name="ID"
								value="${basket.id}" />
						</div>

						<div class=col-md-2>
							<input id="quantity" class="form-control input-md"
								name="Quantity" value="${basket.quantity}" />
						</div>

						<div class=col-md-1>
							<input id="sum" class="form-control input-md" name="Sum"
								value="${basket.sum}" />
						</div>
						<div class=col-md-2>
							<input id="fk_buyers" class="form-control input-md"
								name="FK_buyers" value="${basket.buyerId}" />
						</div>

						<br>
						<br>

						<div class="container">
							<div class="row">
								<div class="col-md-7">№ товара</div>
								<div class="col-md-2"></div>
								<div class="col-md-1"></div>

							</div>
						</div>
						<br>

						<div class="container">
							<div class="row">
								<div class=col-md-7>
									<input id="fk_goods" class="form-control input-md"
										name="FK_goods" value="${basket.goodId}" /> <select
										id="good" name="FK_goods" class="form-control">
										<c:forEach items="${goods}" var="good">
											<form class="update-good-${good.id}">

												<option value="${good.id}" good=${good.id
													} ${good.id==basket.goodId?"selected":""}>

                                           <%-- <div class="col-md-2">${good.name}</div> --%>
                                           <div class="col-md-1">${good.price}</div>
                                           <div class="col-md-1">${good.specificationGoodId}</div>
                                           <div class="col-md-1">${good.measureId}</div>
             

                                      </option>


											</form>

										</c:forEach>
									</select>
								</div>

								<div class=col-md-1>
									<button id="btnUpdateGoodInBasket" value="btnUpdateGoodInBasket" name="btnUpdateGoodInBasket"
										class="btn btn-success">Изменить</button>
								</div>

								<div class=col-md-1>
									<button id="btnDeleteGoodInBasket" value="btnDeleteGoodInBasket" name="btnDeleteGoodInBasket"
										class="btn btn-danger">Удалить</button>
								</div>

							</div>
						</div>
				</form>
				<br>
				<br>
			</c:forEach>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="postbutton"></label>
				<div class="col-md-4">
					<button id="btnMakeOrder" value="btnMakeOrder" name="btnMakeOrder"
						class="btn btn-primary">Оформить</button>
				</div>
			</div>

		</div>

	</fieldset>

	<%@ include file="include/end-html.jspf"%>

</body>
</html>