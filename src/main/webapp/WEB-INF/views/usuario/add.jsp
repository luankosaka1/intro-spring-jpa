<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <spring:url var="css" value="/static/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="${css }"/>
</head>
<body>

<div class="container">
	<h1>Adicionar Usuário</h1>

	<spring:url
		value="${usuario.id == null ? '/usuario/save' : '/usuario/update' }"
		var="save" />
	<form:form modelAttribute="usuario" action="${save }" method="post">
		<form:hidden path="id" />

		<div class="form-group">
			<label for="nome">Nome: </label>
			<form:input path="nome" class="form-control" />
			<form:errors path="nome" cssClass="label label-danger" />
		</div>
		<div class="form-group">
			<label for="sobrenome">Sobrenome: </label>
			<form:input path="sobrenome" class="form-control" />
			<form:errors path="sobrenome" cssClass="label label-danger" />
		</div>
		<div class="form-group">
			<label for="sexo">Sexo: </label>
			<form:select path="sexo" class="form-control">
				<form:options items="${sexos }" itemLabel="desc" />
			</form:select>
		</div>
		<div class="form-group">
			<label for="dtNascimento">Data Nascimento:</label>
			<form:input path="dtNascimento" class="form-control" type="date" />
			<form:errors path="dtNascimento" cssClass="label label-danger" />
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary">Confirmar</button>
		</div>
	</form:form>
</div>
</body>
</html>