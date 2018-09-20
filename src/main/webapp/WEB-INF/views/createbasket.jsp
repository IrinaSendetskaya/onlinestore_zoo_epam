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

<title>Интернет-магазин</title>
</head>

<body>

	<%@ include file="include/begin-html.jspf"%>


	<fieldset>

		<!-- Form Name -->
		<legend>Формирование заказа</legend>

		<div class="container">
			<div class="page-header">
				<h3>Ваша корзина</h3>
				<p class="lead"></p>
			</div>

		</div>

		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-2">Товар</div>
			<div class="col-md-2">Количество</div>
			<hr>
			<div class="col-md-1">Сумма</div>
			<div class="col-md-1">Дата заказа</div>
			<div class="col-md-1">Статус заказа</div>
			<div class="col-md-1"></div>
			<div class="col-md-1"></div>
			<hr>
			<div class="col-md-1"></div>

		</div>
		<br>

		<div class="container">


			<c:forEach items="${basketsListForJsp}" var="basket">
				<form class="update-basket-${basket.id}"
					action="do?command=CreateBasket" method=POST>
					<div class="row">
						<div class="col-md-1">
							<input id="id" class="form-control input-md" name="id"
								type="hidden" class="form-control" value="${basket.id}" />
						</div>

						<div class="col-md-2">${basket.name}</div>
						<div class=col-md-2>
							<input id="quantity" class="form-control input-md"
								name="quantity" value="${basket.quantity}" />
						</div>
						<hr>
						<div class="col-md-1">${basket.sum}</div>

						<div class="col-md-1">${basket.dateOrders}</div>
						<div class="col-md-1">${basket.statusOrders}</div>
						<div class="col-md-1">
							<input id="id" class="form-control input-md" name="fk_goods"
								type="hidden" class="form-control" value="${basket.goodId}" />
						</div>
						<div class=col-md-1>
							<button id="btnUpdateGoodInBasket" value="btnUpdateGoodInBasket"
								name="btnUpdateGoodInBasket" class="btn btn-success">Изменить</button>
						</div>
						<hr>
						<div class=col-md-1>
							<button id="btnDeleteGoodInBasket" value="btnDeleteGoodInBasket"
								name="btnDeleteGoodInBasket" class="btn btn-danger">Удалить</button>
						</div>
						<br>
					</div>

				</form>
			</c:forEach>

			<hr>
			
			<c:if test="${basketsListForJsp!=null}">
			<form action="do?command=ConfirmOrder" method=POST>
				<label class="col-md-4 control-label" for="postbutton"></label>
				
				<div class="col-md-4" hidden="">${sumReady}</div>
				<div class="col-md-4">
					<button id="btnMakeOrder" value="btnMakeOrder" name="btnMakeOrder"
						class="btn btn-primary">Оформить заказ</button>
				</div>
			</form>

			<br> <br>

			<hr>

			
			<div class="row">
				<mytag:paginator count="${goodsSize}" step="5"
					urlprefix="do?command=CreateBasket&start=" />
			</div>
			</c:if>

			<hr>

			<br> <br>
			<div class="container">
				<div class="row">
					<div class="col-md-1"
						style="border-left-color: aqua; font-size: large;">Итого:</div>

					<div class="col-md-8" align="right" style="float: right;">${sumReady}</div>
					<div class="col-md-2" align="right" style="float: right;">рублей</div>
				</div>
			</div>
			<hr>
			<br> <br>

			<div class="col-md-4">Адрес доставки:</div>
			<br>
			<div class="col-md-4">${buyer.address}</div>
			<hr>
			<br>

			<div class="col-md-3">Телефон для связи:</div>
			<br>
			<div class="col-md-3">${buyer.mobile}</div>
			<hr>
			<br> <br>
			<!-- Button -->

		</div>

	</fieldset>

	<%@ include file="include/end-html.jspf"%>

</body>
</html>