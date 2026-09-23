<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<input type="text" id="name" name="name" required> <br>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="4" cols="50" required></textarea> <br>
			<label for="budget">Budget :</label>
			<input type="number" id="budget" name="budget" step="0.01" min="0"> <br>
			<label for="statut">Statut :</label>
			<select name="statut" id="statut">
				<option value="EN_COURS" selected>EN COURS</option>
				<option value="TERMINE">TERMINE</option>
			</select> <br>
			<label for="startDate">Date de debut : </label>
			<input type="date" id="startDate" name="startDate" required> <br>
			<label for="endDate">Date de fin : </label>
			<input type="date" id="endDate" name="endDate">
			<br><br>
			<input type="submit" value="Créer le projet">
			<div id="errorMessage"></div>
		</form>
	</body>
</html>