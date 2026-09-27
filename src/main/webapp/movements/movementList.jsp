<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Liste des mouvements</title>
	</head>
	<body>
		<h1>Liste des mouvements du projet : ${project.name}</h1>
		<c:if test="${param.deleted=='1'}">
			<div class="alert-success">Mouvement supprimer avec succes !!</div>
		</c:if>
		<a href="${pageContext.request.contextPath}/projectDetail?id=${project.id}" class="btn-retour">← Retour au projet</a>
		<table class="project-table">
			<thead>
				<tr>
					<th>Nom</th>
					<th>Libelle</th>
					<th>montant</th>
					<th>Type de mouvement</th>
					<th>Date du mouvement</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${movements}" var="line">
					<tr>
						<td><c:out value="${line.name}"/></td>
						<td><c:out value="${line.libelle}"/></td>
						<td><c:out value="${line.montant}"/></td>
						<td><c:out value="${line.type}"/></td>
						<td><c:out value="${line.date}"/></td>
						<td><a href="${pageContext.request.contextPath}/movementDetail?id=${line.id}">Voir details</a></td>
					</tr>
				</c:forEach>

				<c:if test="${empty movements}">
					<tr>
						<td colspan="6">Aucun mouvement associe a ce projet ! </td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div class="project-actions">
			<a class="project-create" href="${pageContext.request.contextPath}/movementCreate?projectId=${project.id}">+ Nouveau mouvement</a>
		</div>
	</body>
</html>