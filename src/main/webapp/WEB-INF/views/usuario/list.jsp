<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<spring:url value="/static/css/bootstrap.css" var="css" />
<link type="text/css" rel="stylesheet" href="${css }" />
</head>
<body>
<div class="container">
	<h1>Lista de Usuários</h1>

	<h5>${message == null ? '&nbsp;' : message }</h5>
	
	<spring:url var="act_sexo" value="/usuario/sexo" />
	<form action="${act_sexo }" method="get">
		<div class="form-group">
			<label for="tipoSexo">Busca por Sexo</label>
			<select name="tipoSexo" class="form-control">
				<c:forEach var="sexo" items="${sexos }">
				<option value="${sexo.desc }">${sexo.desc }</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Localizar</button>
		</div>
	</form>
	
	<spring:url var="act_nome" value="/usuario/nome" />
	<form action="${act_nome }" method="get">
		<div class="form-group">
			<label for="nome">Busca por Nome ou Sobrenome</label>
			<input type="text" name="nome" class="form-control" />
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-default">Localizar</button>
		</div>
	</form>

	<spring:url value="/usuario/cadastro" var="cadastro" />
	<a href="${cadastro}" title="Cadastrar" class="btn btn-primary">Cadastrar</a>

	<table class="table table-striped table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>NOME</th>
				<th>DATA NASCIMENTO</th>
				<th>SEXO</th>
				<th>AÇÃO</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="usuario" items="${usuarios }">
				<tr>
					<td>${usuario.id }</td>
					<td>${usuario.nome } &nbsp; ${usuario.sobrenome }</td>
					<td>
						<fmt:parseDate var="date" value="${usuario.dtNascimento }" pattern="yyyy-MM-dd" type="date" /> 
						<fmt:formatDate value="${date }" pattern="dd/MM/yyyy" type="date" /></td>
					<td>
						${usuario.sexo.desc }
					</td>
					<td>
						<spring:url value="/usuario/update/${usuario.id }" var="update" /> 
						<a class="btn btn-info" href="${update }">Editar</a>

						<spring:url value="/usuario/delete/${usuario.id }" var="delete" />
						<a class="btn btn-danger" href="${delete }">Excluir</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>