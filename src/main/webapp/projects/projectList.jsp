<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"  %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>liste des projets</title>
	</head>
	<body>
		<h1>Liste des Projets : </h1>
		<table class="project-table">
			<thead>
				<tr>
					<th>Nom</th>
					<th>Description</th>
					<th>Budget</th>
					<th>Statut</th>
					<th>Date de debut</th>
					<th>Date de fin</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${projects}" var="line">
					<tr>
						<td>${line.name}</td>
						<td>${line.description}</td>
						<td>${line.budget}</td>
						<td>${line.statut}</td>
						<td>${line.startDate}</td>
						<td>${line.endDate}</td>
						<td><a href="">Voir details</a></td>
					</tr>
				</c:forEach>

				<c:if test="${empty projects}">
					<tr>
						<td colspan="6">Aucun projet trouvee ! </td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div class="project-actions">
			<a class="project-create" href="projectCreate">+ Nouveau projet</a>
		</div>
	</body>
</html>