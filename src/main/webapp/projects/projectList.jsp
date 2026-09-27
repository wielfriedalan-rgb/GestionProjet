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
		<c:if test="${param.deleted=='1'}">
			<div class="alert-success">Projet supprimer avec succes !!</div>
		</c:if>
		<a href="${pageContext.request.contextPath}/index.html" class="btn-retour">← Retour à l'accueil</a>
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
						<td><c:out value="${line.name}" /></td>
						<td><c:out value="${line.description}" /></td>
						<td><c:out value="${line.budget}" /></td>
						<td>
							<c:if test="${line.statut=='EN_COURS'}">EN COURS</c:if>
							<c:if test="${line.statut=='TERMINE'}">TERMINE</c:if>
						</td>
						<td><c:out value="${line.startDate}" /></td>
						<td><c:out value="${line.endDate}" /></td>
						<td><a href="${pageContext.request.contextPath}/projectDetail?id=${line.id}">Voir details</a></td>
					</tr>
				</c:forEach>

				<c:if test="${empty projects}">
					<tr>
						<td colspan="7">Aucun projet trouvee ! </td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div class="project-actions">
			<a class="project-create" href="${pageContext.request.contextPath}/projectCreate">+ Nouveau projet</a>
		</div>
	</body>
</html>