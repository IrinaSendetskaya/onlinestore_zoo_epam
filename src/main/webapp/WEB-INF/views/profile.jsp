<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}"/>	
<fmt:bundle basename="localization.local" prefix = "profile.">
	<fmt:message key="title" var="title"/>
	<fmt:message key="page_header" var="page_header"/>
	<fmt:message key="label_name" var="label_name"/>
	<fmt:message key="label_password" var="label_password"/>
	<fmt:message key="placeholder_password" var="placeholder_password"/>
	<fmt:message key="label_email" var="label_email"/>
	<fmt:message key="label_mobile" var="label_mobile"/>
	<fmt:message key="label_address" var="label_address"/>
	<fmt:message key="btn_update" var="btn_update"/>
	<fmt:message key="order_header" var="order_header"/>
	<fmt:message key="label_quantity" var="label_quantity"/>
	<fmt:message key="label_amount" var="label_amount"/>
	<fmt:message key="label_date" var="label_date"/>
	<fmt:message key="label_status" var="label_status"/>
	<fmt:message key="label_buyer" var="label_buyer"/>
	<fmt:message key="label_good" var="label_good"/>
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


<form class="form-horizontal"  action="do?command=Profile" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>${page_header}</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="nickname">${label_name}</label>
            <div class="col-md-4">
                <input id="nickname" name="nickname" type="text" placeholder="" class="form-control input-md"
                       value="${buyer.nickname}">
                <span class="help-block">${label_name} (hint)</span>
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">${label_password}</label>
            <div class="col-md-4">
                <input id="password" name="password" type="text" placeholder="placeholder" class="form-control input-md"
                       value="${buyer.password}">
                <span class="help-block">${placeholder_password}</span>
            </div>
        </div>
        
        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="email">${label_email}</label>
            <div class="col-md-4">
                <input id="email" name="email" type="text" placeholder="placeholder" class="form-control input-md"
                       value="${buyer.email}">
            </div>
        </div>
        
        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="mobile">${label_mobile}</label>
            <div class="col-md-4">
                <input id="mobile" name="mobile" type="text" placeholder="placeholder" class="form-control input-md"
                       value="${buyer.mobile}">
            </div>
        </div>
        
        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="address">${label_address}</label>
            <div class="col-md-4">
                <input id="address" name="address" type="text" placeholder="placeholder" class="form-control input-md"
                       value="${buyer.address}">
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="btnChangeProfile" name="btnChangeProfile" class="btn btn-primary">${btn_update}</button>
            </div>
        </div>

    </fieldset>
</form>

<div class="page-header">
  <h1> ${order_header}</h1>
  <p class="lead"></p>
</div>

<div class="row">
   <div class="col-md-1">№</div>
   <div class="col-md-2">${label_quantity}</div>
   <div class="col-md-1">${label_amount}</div>
   <div class="col-md-2">${label_date}</div>
   <div class="col-md-2">${label_status}</div>
   <div class="col-md-2">№ ${label_buyer}</div>
   <div class="col-md-2">№ ${label_good}</div>


</div>

<c:forEach items="${baskets}" var="basket">
   <br>
   <div class="row">
      <div class="col-md-1">${basket.id}</div>
      <div class="col-md-2">${basket.quantity}</div>
      <div class="col-md-1">${basket.sum}</div>
      <div class="col-md-2">${basket.dateOrders}</div>
      <div class="col-md-2">${basket.statusOrders}</div>
      <div class="col-md-2">${basket.buyerId}</div>
      <div class="col-md-2">${basket.goodId}</div>


   </div>
</c:forEach>

<hr>

<div class="row">
    <mytag:paginator count="${goodsSize}" step="5" urlprefix="do?command=Profile&start="/>
</div>

<hr>

<br><br>


	<%@ include file="include/end-html.jspf"%>

</body>
</html>