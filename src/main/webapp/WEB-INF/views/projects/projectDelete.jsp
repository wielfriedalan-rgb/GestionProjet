<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Supprimer le projet</title>
	</head>
	<body>
		<h1>Supprimer le projet : <span style="color: rgb(0, 49, 7);"><c:out value="${project.name}"/></span></h1>
		<form action="projectDelete" method="post">
			<label>Etes-vous sure de supprimer ce projet ? L'action est irreversible.</label>
			<input type="hidden" name="id" value="${project.id}">
			<input type="submit" value="Supprimer le projet">
			<a href="${pageContext.request.contextPath}/projectList" class="btn-annuler">Annuler</a>
			<div id="errorMessage"></div>
		</form>
	</body>
</html>