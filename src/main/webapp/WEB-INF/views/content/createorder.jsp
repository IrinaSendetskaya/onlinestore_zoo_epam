<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
  
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
    
 <!DOCTYPE html >
 <html>  
 
 <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <body>

	<fieldset>
	
	<!-- Form Name -->
	<legend>Форма заказа</legend>
	
	 <div class="container">
	    <div class="page-header">
	      <h2> Аренда</h2>
	      <p class="lead"></p>
	    </div>
	
	 </div>
	
	 <div class="container">
	    <c:forEach items="${ordercar}" var="order">
	            
	                <div class="row">
	                   <div class="col-md-2">№ заказа</div>
	                 
	                   <div class="col-md-2">№ пользователя</div>
	                   <div class="col-md-2">№ авто</div>
	
	                   <div class="col-md-2"><h4>Выберите период аренды:</h4>
							<p>начало:</p>
							<p>
							<input type="date" name="start" min="2012-04-18">
							</p>
							<p>конец:</p>
							<p>
							<input type="date" name="end" >
							</p>
							<br>
							
							</div>
							
	                </div>
	                <br>
	
	
	                <div class="row">
	                    <div class=col-md-2>
	                        <input id="id" class="form-control input-md" name="id"
	                              value="${order.id}"/>
	                    </div>
	
	                    <div class=col-md-2>
	                        <input id="idUser" class="form-control input-md" name="idUser"
	                               value="${order.idUser}"/>
	                    </div>
	
	                    
	                    <div class=col-md-2>
	                        <input id="idCar" class="form-control input-md" name="idCar"
	                               value="${order.idCar}"/>
	                    </div>
	
	                     <br><br>
	
	               <div class="container">
	                  <div class="row">
	                      <div class="col-md-6">№ авто</div>
	                      <div class="col-md-2"></div>
	                      <div class="col-md-1"></div>
	
	                   </div>
	               </div>
	               <br>
	
	               <div class="container">
	                  <div class="row">
	                    <div class=col-md-6>
	                        <input id="idCar" class="form-control input-md" name="idCar"
	                            value="${idCar}"/>
	
	                        <select id="car" name="idCar" class="form-control">
	                                  <c:forEach items="${carlist}" var="car">
	                                     <form class="update-good-${car.id}" >
	
	                                      <option value="${car.id}" good=${car.id} ${car.id==order.idCar?"selected":""}>
	
	                                           <div class="col-md-3">${car.brand}</div>
		                                       <div class="col-md-3">${car.model}</div>
	                                           
	
	                                      </option>
	
	
	                                     </form>
	
	                                  </c:forEach>
	                         </select>
	                     </div>
	
	                     <div class=col-md-1>
	                          <button id="Update" value="Update" name="Update" class="btn btn-success">
	                                   Изменить
	                          </button>
	                     </div>
	
	                     <div class=col-md-1>
	                         <button id="Delete" value="Delete" name="Delete" class="btn btn-danger">
	                                    Удалить
	                         </button>
	                     </div>
	
	                  </div>
	               </div>
	
	            </form>
	            <br><br>
	    </c:forEach>
	
	    <!-- Button -->
	    <div class="form-group">
	      <label class="col-md-4 control-label" for="postbutton"></label>
	      <div class="col-md-4">
	        <button id="ready" value="ready" name="ready" class="btn btn-primary">Заказать</button>
	      </div>
	    </div>
	
	 </div>
	
	</fieldset>
	
	</body>
	
</html>

