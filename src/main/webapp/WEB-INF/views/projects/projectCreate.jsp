<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Creez un nouveau projet</title>
	</head>
	<body>
		<h1>Nouveau Projet</h1>
		<form action="projectCreate" method="post">
			<label for="name">Nom :</label>
			<input type="text" id="name" name="name" value="<c:out value='${param.name}'/>" maxlength="50" required>
			<span class="error">${error['Le nom']}</span>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="4" cols="50" maxlength="2000"><c:out value='${param.description}'/></textarea>
			<span>${error['La description']}</span>
			<label for="budget">Budget :</label>
			<input type="number" id="budget" name="budget" step="0.01" min="0" value="<c:out value='${param.budget}'/>" required>
			<span class="error">${error['Le budget']}</span>
			<label for="statut">Statut :</label>
			<select name="statut" id="statut">
				<option value="EN_COURS" ${param.statut=='EN_COURS' || empty param.statut ? 'selected' : ''}>EN COURS</option>
				<option value="TERMINE" ${param.statut=='TERMINE' ? 'selected' : ''}>TERMINE</option>
			</select>
			<span class="error">${error['Le statut']}</span>
			<label for="startDate">Date de debut : </label>
			<input type="date" id="startDate" name="startDate" value="<c:out value='${param.startDate}'/>" required>
			<span class="error">${error['La date de debut']}</span>
			<label for="endDate">Date de fin : </label>
			<input type="date" id="endDate" name="endDate" value="<c:out value='${param.endDate}'/>" required>
			<span class="error">${error['La date de fin']}</span>
			<br>
			<div id="errorMessage"><c:if test="${not empty error}">Vous avez entré une ou plusieurs information(s) non correcte. Veuillez vérifier !</c:if></div>
			<br>
			<input type="submit" value="Créer le projet">
			<a href="${pageContext.request.contextPath}/projectList" class="btn-annuler">Annuler</a>
		</form>
	</body>
</html>