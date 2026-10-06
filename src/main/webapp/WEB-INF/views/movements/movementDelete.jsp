<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Supprimer le mouvement</title>
	</head>
	<body>
		<h1>Supprimer le mouvement : <span style="color: rgb(0, 49, 7);"><c:out value="${movement.name}"/></span></h1>
		<form action="movementDelete" method="post">
			<label>Etes-vous sure de supprimer ce mouvement ? L'action est irreversible.</label>
			<input type="hidden" name="id" value="${movement.id}">
			<input type="hidden" name="projectId" value="${movement.projectId}">
			<input type="submit" value="Supprimer le mouvement">
			<a href="${pageContext.request.contextPath}/movementDetail?id=${movement.id}" class="btn-annuler">Annuler</a>
			<div id="errorMessage"></div>
		</form>
	</body>
</html>