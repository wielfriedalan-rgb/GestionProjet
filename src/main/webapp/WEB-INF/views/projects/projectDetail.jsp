<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Details du projet</title>
	</head>
	<body>
		<h1>Details du projet : <span style="color: rgb(0, 49, 7); max-width: 420px;"><c:out value="${project.name}"/></span></h1>
		<c:if test="${param.deleteError=='1'}">
			<div class="alert-error">Impossible de supprimer ce projet car il est associe a un ou plusieurs mouvement(s).</div>
		</c:if>
		<div class="detailProject">
			<div class="detailsProject">
				<p><span class="label">Nom :</span><span class="value"><c:out value="${project.name}" /></span></p>
				<p><span class="label">Description :</span><span class="value"><c:out value="${project.description}" /></span></p>
				<p><span class="label">Budget :</span><span class="value"><c:out value="${project.budget}" /></span></p>
				<p><span class="label">Statut :</span><span class="value">${project.statut=='EN_COURS'?'EN COURS':'TERMINE'}</span></p>
				<p><span class="label">Date de debut :</span ><span class="value"><c:out value="${project.startDate}" /></span></p>
				<p><span class="label">Date de Fin :</span><span class="value"><c:out value="${project.endDate}" /></span></p>
			</div>
			<br>
			<div class="btn-action">
				<a href="${pageContext.request.contextPath}/project?page=Update&id=${project.id}">Modifier</a> | 
				<a href="${pageContext.request.contextPath}/movement?page=List&projectId=${project.id}">Voire les mouvements</a> | 
				<a href="${pageContext.request.contextPath}/financeSituation?id=${project.id}">Situation financiere</a> | 
				<a href="${pageContext.request.contextPath}/project?page=Delete&id=${project.id}">Supprimer ce projet</a> | 
				<a href="${pageContext.request.contextPath}/project?page=List" class="btn-retour">retour a la liste</a>
			</div>
		</div>
	</body>
</html>