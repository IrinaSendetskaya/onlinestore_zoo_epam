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
	<%-- <div class="container">
		<p>${searchMsgLoc}</p>
		<form class="read-good" action="do?command=ReadGoods" method=POST>
			<div class="row">
				<div class=col-md-6>
					<select id="filmId" class="form-control" name="filmId"
						required="required">
						<c:forEach items="${filmlist}" var="film">
							<option value="${film.id}">${film.filmName}</option>
						</c:forEach>
					</select>
				</div>
				<button id="read" value="read" name="crudCommand"
					class="col-md-1 btn btn-success">${searchButtonLoc}</button>
			</div>
		</form>
	</div>
	<hr>
	<c:if test="${foundFilm!=null}">
		<br>
		<div class="container">
			<form class="update-user" action="cinema?action=crud_film"
				method=POST>

				<div class="row">
					<div class=col-md-3>ID :</div>
					<div class=col-md-9>
						<input id="filmId" class="form-control input-md" name="filmId"
							value="${foundFilm.id}" readonly="readonly" />
					</div>
				</div>
				<div class="row">
					<div class=col-md-3>${filmNameLoc}:</div>
					<div class=col-md-9>
						<input id="filmName" class="form-control input-md" name="filmName"
							value="${foundFilm.filmName}" />
					</div>
				</div>
				<div class="row">
					<div class=col-md-3>${posterURLLoc}:</div>
					<div class=col-md-9>
						<input id="filmPosterUrl" class="form-control input-md"
							name="filmPosterUrl" value="${foundFilm.posterUrl}" />
					</div>
				</div>
				<div class="row">
					<div class=col-md-3>${videoIdLoc}:</div>
					<div class=col-md-9>
						<input id="filmYouTubeVideoId" class="form-control input-md"
							name="filmYouTubeVideoId" value="${foundFilm.youTubeVideoId}" />
					</div>
				</div>

				<div class="row">
					<div class=col-md-3>${description}:</div>
					<div class=col-md-9>
						<textarea id="filmDescription" name="filmDescription" cols="100"
							rows="50">${foundFilm.description} </textarea>
					</div>
				</div>
				<div class="row">
					<div class=col-md-3>${sections}:</div>
					<div class=col-md-5>
						<select id="section" class="form-control" name="fk_sections"
							multiple="multiple" size="5" required>
							<option disabled>${msg_sections}</option>
							<c:forEach items="${sections}" var="section">
								<option value="${section.id}"
									${foundFilm.genres.contains(section)?"selected":""}>
									${section.section}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<select id="genre" class="form-control" name="filmGenresId"
								multiple="multiple" size="5" required>
								<option disabled>${chooseGenresMsgLoc}</option>
								<c:forEach items="${genrelist}" var="genre">
									<option value="${genre.id}">${genre.genreName}</option>
								</c:forEach>
							</select>
							
							
							
							
				<button id="btnUpdateGood" value="btnUpdateGood"
					name="btnUpdateGood" class="btn btn-success">${btn_update}</button>

				<button id="btnDeleteGood" value="btnDeleteGood"
					name="btnDeleteGood" class="btn btn-danger">${btn_delete}</button>
			</form>

		</div>
	</c:if> --%>
	
	<%@ include file="include/end-html.jspf"%>

</body>
</html>
