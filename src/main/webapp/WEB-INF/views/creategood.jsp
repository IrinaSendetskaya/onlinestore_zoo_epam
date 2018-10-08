<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}" />
<fmt:bundle basename="localization.local" prefix="creategood.">
	<fmt:message key="title" var="title" />
	<fmt:message key="label_good" var="label_good" />
	<fmt:message key="label_image" var="label_image" />
	<fmt:message key="label_price" var="label_price" />
	<fmt:message key="label_measure" var="label_measure" />
	<fmt:message key="msg_measure" var="msg_measure" />
	<fmt:message key="label_description" var="label_description" />
	<fmt:message key="label_sections" var="label_sections" />
	<fmt:message key="msg_sections" var="msg_sections" />
	<fmt:message key="btn_update" var="btn_update" />
	<fmt:message key="btn_delete" var="btn_delete" />
	<fmt:message key="btn_create" var="btn_create" />
</fmt:bundle>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>

<title>${title}</title>
</head>

<body>

	<%@ include file="include/begin-html.jspf"%>


	<div class="container">
		<button class="btn btn-success btn-lg" type="button"
			data-toggle="collapse" data-target="#collapseExample"
			aria-expanded="false" aria-controls="collapseExample">${btn_create}</button>

		<div class="collapse" id="collapseExample">
			<div class="card card-body">
				<div class="container">
					<div class="row">
						<div class=col-md-2>${label_good}</div>
						<div class=col-md-3>${label_description}</div>
						<div class=col-md-2>${label_image}</div>
						<div class=col-md-1>${label_price}</div>
						<div class=col-md-2>${label_measure}</div>
						<div class=col-md-2>${label_sections}</div>
					</div>
				</div>

				<form class="create-film" action="do?command=CreateGood" method=POST>
					<div class="row">
						<div class=col-md-2>
							<input id="name" class="form-control input-md" name="name" />
						</div>
						<div class=col-md-3>
							<input id="description" class="form-control input-md"
								name="description" />
						</div>
						<div class=col-md-2>
							<input id="imageUrl" class="form-control input-md"
								name="imageUrl" />
						</div>
						<div class=col-md-1>
							<input id="price" class="form-control input-md" name="price" />
						</div>
						<div class=col-md-2>
							<select id="id" class="form-control" name="id">
								<option disabled>${msg_measure}</option>
								<c:forEach items="${measures}" var="measure">
									<option value="${measure.id}">${measure.size}</option>
								</c:forEach>
							</select>
						</div>
						<div class=col-md-2>
							<select id="id" class="form-control" name="id">
								<option disabled>${msg_sections}</option>
								<c:forEach items="${sections}" var="section">
									<option value="${section.id}">${section.sectionTitle}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<button id="btnCreateGood" value="btnCreateGood"
						name="btnCreateGood" class="btn btn-success">ok</button>

				</form>
			</div>
		</div>
	</div>
	<hr>
	
	
	<%@ include file="include/end-html.jspf"%>

</body>
</html>
