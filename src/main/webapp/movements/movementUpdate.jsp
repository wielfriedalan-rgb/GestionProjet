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
		<c:set var="varName" value="${empty error ? movement.name : param.name}" />
		<c:set var="varLibelle" value="${empty error ? movement.libelle : param.libelle}" />
		<c:set var="varDescription" value="${empty error ? movement.description : param.description}" />
		<c:set var="varMontant" value="${empty error ? movement.montant : param.montant}" />
		<c:set var="varType" value="${empty error ? movement.type : param.type}" />
		<c:set var="varDate" value="${empty error ? movement.date : param.date}" />
	
		<h1>Modifier un mouvement</h1>
		<form action="movementUpdate" method="post">
			<input type="hidden" name="id" value="${movement.id}">
			<input type="hidden" name="projectId" value="${movement.projectId}">
			<label for="name">Nom :</label>
			<input type="text" id="name" name="name" value="<c:out value='${varName}' />">
			<span class="error">${error['Le nom']}</span>
			<label for="libelle">Libelle :</label>
			<textarea id="libelle" name="libelle" rows="4" cols="50" required><c:out value='${varLibelle}' /></textarea>
			<span class="error">${error['Le libelle']}</span>
			<label for="montant">Montant :</label>
			<input type="number" id="montant" name="montant" value="<c:out value='${varMontant}' />" step="0.01" min="0.01" required>
			<span class="error">${error['Le montant']}</span>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="4" cols="50"><c:out value='${varDescription}' /></textarea>
			<span class="error">${error['La description']}</span>
			<label for="type">Type de mouvement :</label>
			<select name="type" id="type" required>
				<option value="ENTREE" <c:if test="${varType=='ENTREE'}">selected</c:if>>ENTREE</option>
				<option value="SORTIE" <c:if test="${varType=='SORTIE'}">selected</c:if>>SORTIE</option>
			</select>
			<span class="error">${error['Le type']}</span>
			<label for="date">Date de creation du mouvement : </label>
			<input type="date" id="date" name="date" value="<c:out value='${varDate}' />" required>
			<span class="error">${error['La date']}</span>
			<br>
			<div id="errorMessage"><c:if test="${not empty error}">Vous avez entré une ou plusieurs information(s) non correcte. Veuillez vérifier !</c:if></div>
			<br>
			<input type="submit" value="Modifier le mouvement">
			<a href="${pageContext.request.contextPath}/movementDetail?id=${movement.id}" class="btn-annuler">Annuler</a>
		</form>
	</body>
</html>