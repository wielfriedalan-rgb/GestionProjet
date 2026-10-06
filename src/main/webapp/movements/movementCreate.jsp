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
			<input type="text" id="name" name="name" value="<c:out value='${param.name}'/>">
			<span class="error">${error['Le nom']}</span>
			<label for="libelle">Libelle :</label>
			<textarea id="libelle" name="libelle" rows="4" cols="50" required><c:out value='${param.libelle}'/></textarea>
			<span class="error">${error['Le libelle']}</span>
			<label for="montant">Montant :</label>
			<input type="number" id="montant" name="montant" value="<c:out value='${param.montant}'/>" step="0.01" min="0.01" required>
			<span class="error">${error['Le montant']}</span>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="4" cols="50"><c:out value='${param.description}'/></textarea>
			<span class="error">${error['La description']}</span>
			<label for="type">Type de mouvement :</label>
			<select name="type" id="type" required>
				<option value="ENTREE" ${param.type=='ENTREE' || empty param.type ? 'selected' : ''}>ENTREE</option>
				<option value="SORTIE" ${param.type=='SORTIE' ? 'selected' : ''}>SORTIE</option>
			</select>
			<span class="error">${error['Le type']}</span>
			<label for="date">Date de creation du mouvement : </label>
			<input type="date" id="date" name="date" value="<c:out value='${param.date}'/>" required>
			<span class="error">${error['La date']}</span>
			<br>
			<div id="errorMessage"><c:if test="${not empty error}">Vous avez entré une ou plusieurs information(s) non correcte. Veuillez vérifier !</c:if></div>
			<br>
			<input type="submit" value="Créer le mouvement">
			<a href="${pageContext.request.contextPath}/movementList?projectId=${project.id}" class="btn-annuler">Annuler</a>
		</form>
	</body>
</html>