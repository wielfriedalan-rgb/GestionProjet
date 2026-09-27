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
		<h1>Modifier Projet</h1>
		<form action="projectUpdate?id=${project.id}" method="post">
			<label for="name">Nom :</label>
			<input type="text" id="name" name="name" value="<c:out value='${project.name}'/>" required> <br>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="4" cols="50" required><c:out value='${project.description}'/></textarea> <br>
			<label for="budget">Budget :</label>
			<input type="number" id="budget" name="budget" value="<c:out value='${project.budget}'/>" step="0.01" min="0"> <br>
			<label for="statut">Statut :</label>
			<select name="statut" id="statut">
				<option value="EN_COURS" <c:if test="${project.statut=='EN_COURS'}">selected</c:if>>EN COURS</option>
				<option value="TERMINE" <c:if test="${project.statut=='TERMINE'}">selected</c:if>>TERMINE</option>
			</select> <br>
			<label for="startDate">Date de debut : </label>
			<input type="date" id="startDate" name="startDate" value="<c:out value='${project.startDate}'/>" required> <br>
			<label for="endDate">Date de fin : </label>
			<input type="date" id="endDate" name="endDate" value="<c:out value='${project.endDate}'/>">
			<br><br>
			<div class="action-update">
				<input type="submit" value="Modifier le projet">
				<a href="${pageContext.request.contextPath}/projectDetail?id=${project.id}" class="btn-annuler">Annuler</a>
			</div>
			<div id="errorMessage"></div>
		</form>
	</body>
</html>