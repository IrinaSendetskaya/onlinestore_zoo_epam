<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}"/>	
<fmt:bundle basename="localization.local" prefix = "basket.">
	<fmt:message key="title" var="title"/>
	<fmt:message key="main_header" var="main_header"/>
	<fmt:message key="page_header" var="page_header"/>
	<fmt:message key="label_good" var="label_good"/>
	<fmt:message key="label_quantity" var="label_quantity"/>
	<fmt:message key="label_sum" var="label_sum"/>
	<fmt:message key="label_data" var="label_data"/>
	<fmt:message key="label_status" var="label_status"/>
	<fmt:message key="btn_update" var="btn_update"/>
	<fmt:message key="btn_delete" var="btn_delete"/>
	<fmt:message key="btn_confirm" var="btn_confirm"/>
	<fmt:message key="total" var="total"/>
	<fmt:message key="currency" var="currency"/>
	<fmt:message key="address" var="address"/>
	<fmt:message key="mobile" var="mobile"/>
</fmt:bundle>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<title>${title}</title>
</head>

<body>

	<%@ include file="include/begin-html.jspf"%>


	<fieldset>

		<!-- Form Name -->
		<legend>${main_header}</legend>

		<div class="container">
			<div class="page-header">
				<h3>${page_header}</h3>
				<p class="lead"></p>
			</div>

		</div>

		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-2">${label_good}</div>
			<div class="col-md-2">${label_quantity}</div>
			<hr>
			<div class="col-md-1">${label_sum}</div>
			<div class="col-md-1">${label_data}</div>
			<div class="col-md-1">${label_status}</div>
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
								name="btnUpdateGoodInBasket" class="btn btn-success">${btn_update}</button>
						</div>
						<hr>
						<div class=col-md-1>
							<button id="btnDeleteGoodInBasket" value="btnDeleteGoodInBasket"
								name="btnDeleteGoodInBasket" class="btn btn-danger">${btn_delete}</button>
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
						class="btn btn-primary">${btn_confirm}</button>
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
						style="border-left-color: aqua; font-size: large;">${total}</div>

					<div class="col-md-8" align="right" style="float: right;">${sumReady}</div>
					<div class="col-md-2" align="right" style="float: right;">${currency}</div>
				</div>
			</div>
			<hr>
			<br> <br>

			<div class="col-md-4">${address}</div>
			<br>
			<div class="col-md-4">${buyer.address}</div>
			<hr>
			<br>

			<div class="col-md-3">${mobile}</div>
			<br>
			<div class="col-md-3">${buyer.mobile}</div>
			<hr>
			<br> <br>

		</div>

	</fieldset>

	<%@ include file="include/end-html.jspf"%>

</body>
</html>