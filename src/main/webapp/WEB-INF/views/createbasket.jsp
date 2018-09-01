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

		<div class="row">
			<div class="col-md-1">№</div>
			<div class="col-md-2">Товар</div>
			<div class="col-md-1">Количество</div>
			<div class="col-md-1">Сумма</div>
			<div class="col-md-1">Дата заказа</div>
			<div class="col-md-1">Статус заказа</div>
			<div class="col-md-1">№ покупателя</div>
			<div class="col-md-1">№ товара</div>
			<div class="col-md-1"></div>
			<div class="col-md-1"></div>

		</div>
		<br>

		<div class="container">
			<c:forEach items="${baskets}" var="basket">
				<form class="update-user-${basket.id}"
					action="do?command=CreateBasket" method=POST>

					<div class="row">
						<div class="col-md-1">${basket.id}</div>

						<div class="col-md-2">${nameGood}</div>
						<%-- <div class="col-md-2"
							accesskey='<jsp:attribute value="${nameGood}" name="${nameGood}"/>'>${nameGood}</div> --%>
						<div class=col-md-1>
							<input id="quantity" class="form-control input-md"
								name="quantity" value="${basket.quantity}" />
						</div>
						<div class="col-md-1">${basket.sum}</div>
						
						<div class="col-md-1">${basket.dateOrders}</div>
						<div class="col-md-1">${basket.statusOrders}</div>
						<div class="col-md-1">${basket.buyerId}</div>
						<div class="col-md-1">${basket.goodId}</div>
						
						<div class=col-md-1>
							<button id="btnUpdateGoodInBasket" value="btnUpdateGoodInBasket"
								name="btnUpdateGoodInBasket" class="btn btn-success">Изменить</button>
						</div>
						<div class=col-md-1>
							<button id="btnDeleteGoodInBasket" value="btnDeleteGoodInBasket"
								name="btnDeleteGoodInBasket" class="btn btn-danger">Удалить</button>
						</div>

						

						

						<br>

						<%-- <div class="container">
							<div class="row">
								<div class=col-md-7>
									<input id="fk_goods" class="form-control input-md"
										name="fk_goods" value="${basket.goodId}" /> <select id="good"
										name="FK_goods" class="form-control">
										<c:forEach items="${goodsListForJsp}" var="good">
											<form class="update-good-${good.id}">

												<option value="${good.id}" good=${good.id
													}
													${good.id==basket.goodId?"selected":""}>

													<div class="col-md-2">${good.name}</div>
													<div class="col-md-1">${good.price}</div>
													<div class="col-md-1">${good.specificationGoodId}</div>
													<div class="col-md-1">${good.measureId}</div>


												</option>


											</form>

										</c:forEach>
									</select>
								</div>



							</div> --%>

					</div>
				</form>
				<br>

			</c:forEach>
			<br>

			<div class="col-md-1"
				style="border-left-color: aqua; font-size: large;">Итого:</div>
			<%-- <div class="col-md-2" align="right"
				accesskey='<jsp:attribute value="${sumReady}" name="${sumReady}"/>'>${sumReady}</div> --%>

			<br> <br>

			<div class="col-md-4">Адрес доставки:</div>
			<br>
			<div class="col-md-4">${buyer.address}</div>
			<br>
			<div class="col-md-3">Телефон для связи:</div>
			<br>
			<div class="col-md-3">${buyer.mobile}</div>

			<br> <br>
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