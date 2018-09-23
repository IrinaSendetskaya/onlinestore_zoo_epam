<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${currentLocale}"/>	
<fmt:bundle basename="localization.local" prefix = "edit_buyer.">
	<fmt:message key="title" var="title"/>
	<fmt:message key="label_name" var="label_name"/>
	<fmt:message key="label_email" var="label_email"/>
	<fmt:message key="label_mobile" var="label_mobile"/>
	<fmt:message key="label_address" var="label_address"/>
	<fmt:message key="label_role" var="label_role"/>
	<fmt:message key="btn_update" var="btn_update"/>
	<fmt:message key="btn_delete" var="btn_delete"/>
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
		<div class="row">
			<div class=col-md-1></div>
			<div class=col-md-1>${label_name}</div>
			<div class=col-md-1></div>
			<div class=col-md-2>${label_email}</div>
			<div class=col-md-1>${label_mobile}</div>
			<div class=col-md-2>${label_address}</div>
			<div class=col-md-2>${label_role}</div>
		</div>
	</div>

	<div class="container">
		<c:forEach items="${buyers}" var="buyer">
			<form class="update-user-${buyer.id}" action="do?command=EditBuyer"
				method=POST>
				<div class="row">
					<div class=col-md-1>
						<input id="id" class="form-control input-md" name="id"
							type="hidden" value="${buyer.id}" />
					</div>
					<div class=col-md-1>
						<input id="nickname" class="form-control input-md" name="nickname"
							value="${buyer.nickname}" />
					</div>
					<div class=col-md-1>
						<input id="password" class="form-control input-md" name="password"
							type="hidden" value="${buyer.password}" />
					</div>
					<div class=col-md-2>
						<input id="email" class="form-control input-md" name="email"
							value="${buyer.email}" />
					</div>
					<div class=col-md-1>
						<input id="mobile" class="form-control input-md" name="mobile"
							value="${buyer.mobile}" />
					</div>
					<div class=col-md-2>
						<input id="address" class="form-control input-md" name="address"
							value="${buyer.address}" />
					</div>

					<div class=col-md-2>
						<select id="role" name="fk_roles" class="form-control">
							<c:forEach items="${roles}" var="role">
								<option value="${role.id}" role=${role.id
									}
									${role.id==buyer.roleId?"selected":""}>${role.role}</option>
							</c:forEach>
						</select>
					</div>

					<div class=col-md-1>
						<button id="btnUpdateBuyer" value="btnUpdateBuyer"
							name="btnUpdateBuyer" class="btn btn-success">${btn_update}</button>
					</div>

					<div class=col-md-1>
						<button id="btnDeleteBuyer" value="btnDeleteBuyer"
							name="btnDeleteBuyer" class="btn btn-danger">
							${btn_delete}</button>
					</div>
				</div>
			</form>
		</c:forEach>
	</div>


	<hr>

	<%-- <div class="row">
		<mytag:paginator count="${goodsSize}" step="5"
			urlprefix="do?command=Profile&start=" />
	</div> --%>

	<hr>

	<br>
	<br>
	<%@ include file="include/end-html.jspf"%>

</body>
</html>


