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


<form class="form-horizontal"  action="do?command=ResetDB" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Reset DB</legend>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <button id="btnResetDB" name="btnResetDB" class="btn btn-primary">Reset (or create) DB</button>
            </div>
        </div>

    </fieldset>
</form>

<p>Нажмите на кнопку чтобы создать или сбросить базу sendetskaya (порт 2016)</p>

<p>Cmd Reset DB: <b>${message}</b></p>


	<%@ include file="include/end-html.jspf"%>

</body>
</html>