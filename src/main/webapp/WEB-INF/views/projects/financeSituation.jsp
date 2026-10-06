<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
		<title>Situtation Financiere</title>
	</head>
	<body>
		<h1>Situation financiere du projet : <span style="color: rgb(0, 49, 7); max-width: 420px;"><c:out value="${project.name}"/></span></h1>
		<div class="detailProject">
			<div class="detailsProject">
				<p><span class="label">Total des entrees :</span><span class="value"><c:out value="${finance.totalEntrees}" /></span></p>
				<p><span class="label">Total des sorties :</span><span class="value"><c:out value="${finance.totalSorties}" /></span></p>
				<p><span class="label">Solde :</span><span class="value"><c:out value="${finance.solde}" /></span></p>					
				<p><span class="label">Budget restant :</span><span class="value"><c:out value="${finance.budgetRestant}" /></span></p>
			</div>
			<br>
			<div class="btn-action">
				<a href="${pageContext.request.contextPath}/projectDetail?id=${project.id}" class="btn-retour">retour aux details</a>
			</div>
		</div>
	</body>
</html>