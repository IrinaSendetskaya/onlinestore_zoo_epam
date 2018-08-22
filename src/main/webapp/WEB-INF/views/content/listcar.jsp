<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>start page</title>
</head>
	<body>
		
		<div class="page-header">
		  <h2> Все авто</h2>
		  <p class="lead"></p>
		</div>
	
		<div class="container">
			<div class="row">
			   <div class="col-md-1">№</div>
			   <div class="col-md-2">Бренд</div>
			   <div class="col-md-2">Модель</div>	
	   		   <div class="col-md-2">Действия</div>	
			</div>
			<br>
		
			<c:forEach items="${cars}" var="car">
			   <form class="update-good-${car.id}" action="do?command=Index" method=POST>
			      <div class="row">
			         <div class="col-md-1">${car.id}</div>
			         <div class="col-md-2">${car.brand}</div>					         
			         <div class="col-md-2">${car.model}</div>
			         
			         <div class="col-md-2">
			            <button id="btnOrder" value="Order" name="Order" class="btn btn-success">
			                  Заказать
			            </button>
	        		 </div>
			      </div>		
			   </form>		
			</c:forEach>
		
		</div>
		
		<br><br>
		
	</body>
</html>