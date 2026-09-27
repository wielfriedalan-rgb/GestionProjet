<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Creez un mouvement</title>
	</head>
	<body>
		<h1>Nouveau mouvement pour : <span style="color: rgb(0, 49, 7);"><c:out value="${project.name}"/></span></h1>
		<form action="movementCreate" method="post">
			<input type="hidden" name="projectId" value="${project.id}">
			<label for="name">Nom :</label>
			<input type="text" id="name" name="name" required> <br>
			<label for="libelle">Libelle :</label>
			<textarea id="libelle" name="libelle" rows="4" cols="50" required></textarea> <br>
			<label for="montant">Montant :</label>
			<input type="number" id="montant" name="montant" step="0.01" min="0"> <br>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="4" cols="50" required></textarea> <br>
			<label for="type">Type de mouvement :</label>
			<select name="type" id="type">
				<option value="ENTREE" selected>ENTREE</option>
				<option value="SORTIE">SORTIE</option>
			</select> <br>
			<label for="date">Date de creation du mouvement : </label>
			<input type="date" id="date" name="date" required> <br>
			<br><br>
			<input type="submit" value="Créer le mouvement">
			<a href="${pageContext.request.contextPath}/movementList?projectId=${project.id}" class="btn-annuler">Annuler</a>
			<div id="errorMessage"></div>
		</form>
	</body>
</html>