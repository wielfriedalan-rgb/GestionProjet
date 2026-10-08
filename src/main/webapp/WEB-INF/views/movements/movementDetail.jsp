<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Details du Mouvement</title>
	</head>
	<body>
		<h1>Details du mouvement : <span style="color: rgb(0, 49, 7); max-width: 420px;"><c:out value="${movement.name}"/></span></h1>
		<c:if test="${param.modified=='1'}">
			<div class="alert-success">Mouvement modifier avec succes !!</div>
		</c:if>
		<div class="detailProject">
			<div class="detailsProject">
				<p><span class="label">Nom :</span><span class="value"><c:out value="${movement.name}" /></span></p>
				<p><span class="label">Libelle :</span><span class="value"><c:out value="${movement.libelle}" /></span></p>
				<p><span class="label">Montant :</span><span class="value"><c:out value="${movement.montant}" /></span></p>
				<p><span class="label">Description :</span><span class="value"><c:out value="${movement.description}" /></span></p>
				<p><span class="label">Type :</span><span class="value"><c:out value="${movement.type}" /></span></p>
				<p><span class="label">Date :</span ><span class="value"><c:out value="${movement.date}" /></span></p>
			</div>
			<br>
			<div>
				<a href="${pageContext.request.contextPath}/movement?page=Update&id=${movement.id}&projectId=${movement.projectId}">Modifier ce mouvement</a> | 
				<a href="${pageContext.request.contextPath}/movement?page=Delete&id=${movement.id}&projectId=${movement.projectId}">Supprimer ce mouvement</a> | 
				<a href="${pageContext.request.contextPath}/movement?page=List&projectId=${movement.projectId}" class="btn-retour">retour a la liste</a>
			</div>
		</div>
	</body>
</html>