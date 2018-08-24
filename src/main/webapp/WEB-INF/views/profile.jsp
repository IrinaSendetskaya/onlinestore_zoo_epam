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


<form class="form-horizontal"  action="do?command=Profile" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Form Login</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="nickname">Nickname</label>
            <div class="col-md-4">
                <input id="nickname" name="nickname" type="text" placeholder="" class="form-control input-md" required=""
                       value="${buyer.nickname}">
                <span class="help-block">Nickname (hint)</span>
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password Input</label>
            <div class="col-md-4">
                <input id="password" name="password" type="text" placeholder="placeholder" class="form-control input-md" required=""
                       value="${buyer.password}">
                <span class="help-block">(min 6 symbols)</span>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="btnChangeProfile" name="btnChangeProfile" class="btn btn-primary">Изменить</button>
            </div>
        </div>

    </fieldset>
</form>

<div class="page-header">
  <h1> Корзина</h1>
  <p class="lead"></p>
</div>

<div class="row">
   <div class="col-md-1">№</div>
   <div class="col-md-2">Количество</div>
   <div class="col-md-1">Сумма</div>
   <div class="col-md-2">№ покупателя</div>
   <div class="col-md-2">№ товара</div>


</div>

<c:forEach items="${baskets}" var="basket">
   <br>
   <div class="row">
      <div class="col-md-1">${basket.id}</div>
      <div class="col-md-2">${basket.quantity}</div>
      <div class="col-md-1">${basket.sum}</div>
      <div class="col-md-2">${basket.fk_buyers}</div>
      <div class="col-md-2">${basket.fk_goods}</div>


   </div>
</c:forEach>

<br><br>


	<%@ include file="include/end-html.jspf"%>

</body>
</html>