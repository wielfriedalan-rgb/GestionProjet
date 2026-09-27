<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Modifier le mouvement</title>
	</head>
	<body>
		<h1>Modifier un mouvement</h1>
		<form action="movementUpdate" method="post">
			<input type="hidden" name="id" value="${movement.id}">
			<label for="name">Nom :</label>
			<input type="text" id="name" name="name" value="<c:out value='${movement.name}' />" required> <br>
			<label for="libelle">Libelle :</label>
			<textarea id="libelle" name="libelle" rows="4" cols="50" required><c:out value='${movement.libelle}' /></textarea> <br>
			<label for="montant">Montant :</label>
			<input type="number" id="montant" name="montant" value="<c:out value='${movement.montant}' />" step="0.01" min="0"> <br>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="4" cols="50" required><c:out value='${movement.description}' /></textarea> <br>
			<label for="type">Type de mouvement :</label>
			<select name="type" id="type">
				<option value="ENTREE" <c:if test="${movement.type=='ENTREE'}">selected</c:if>>ENTREE</option>
				<option value="SORTIE" <c:if test="${movement.type=='SORTIE'}">selected</c:if>>SORTIE</option>
			</select> <br>
			<label for="date">Date de creation du mouvement : </label>
			<input type="date" id="date" name="date" value="<c:out value='${movement.date}' />" required> <br>
			<br><br>
			<input type="submit" value="Modifier le mouvement">
			<a href="${pageContext.request.contextPath}/movementDetail?id=${movement.id}" class="btn-annuler">Annuler</a>
			<div id="errorMessage"></div>
		</form>
	</body>
</html>