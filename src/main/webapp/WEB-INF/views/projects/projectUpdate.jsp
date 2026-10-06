<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Modifier le projet</title>
	</head>
	<body>
		<c:set var="varName" value="${empty error ? project.name : param.name}" />
		<c:set var="varDescription" value="${empty error ? project.description : param.description}" />
		<c:set var="varBudget" value="${empty error ? project.budget : param.budget}" />
		<c:set var="varStatut" value="${empty error ? project.statut : param.statut}" />
		<c:set var="varStartDate" value="${empty error ? project.startDate : param.startDate}" />
		<c:set var="varEndDate" value="${empty error ? project.endDate : param.endDate}" />
	
		<h1>Modifier Projet</h1>
		<form action="projectUpdate?id=${project.id}" method="post">
			<label for="name">Nom :</label>
			<input type="text" id="name" name="name" value="<c:out value='${varName}'/>" required>
			<span class="error">${error['Le nom']}</span>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="4" cols="50"><c:out value='${varDescription}'/></textarea>
			<span>${error['La description']}</span>
			<label for="budget">Budget :</label>
			<input type="number" id="budget" name="budget" value="<c:out value='${varBudget}'/>" step="0.01" min="0" required>
			<span class="error">${error['Le budget']}</span>
			<label for="statut">Statut :</label>
			<select name="statut" id="statut">
				<option value="EN_COURS" <c:if test="${varStatut=='EN_COURS'}">selected</c:if>>EN COURS</option>
				<option value="TERMINE" <c:if test="${varStatut=='TERMINE'}">selected</c:if>>TERMINE</option>
			</select>
			<span class="error">${error['Le statut']}</span>
			<label for="startDate">Date de debut : </label>
			<input type="date" id="startDate" name="startDate" value="<c:out value='${varStartDate}'/>" required>
			<span class="error">${error['La date de debut']}</span>
			<label for="endDate">Date de fin : </label>
			<input type="date" id="endDate" name="endDate" value="<c:out value='${varEndDate}'/>" required>
			<span class="error">${error['La date de fin']}</span>
			<br>
			<div id="errorMessage"><c:if test="${not empty error}">Vous avez entré une ou plusieurs information(s) non correcte. Veuillez vérifier !</c:if></div>
			<br>
			<div class="action-update">
				<input type="submit" value="Modifier le projet">
				<a href="${pageContext.request.contextPath}/projectDetail?id=${project.id}" class="btn-annuler">Annuler</a>
			</div>
		</form>
	</body>
</html>