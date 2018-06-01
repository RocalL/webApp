<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>

<%@ page import="model.Projet"%>
<%@ page import="factory.ProjetFactory"%>
<%@ page import="factory.ProjetFactoryImpl"%>

<div class="container">
	<c:set var="projet" value="${requestScope['projet']}" scope="page" />
	<div class="row">
		<h1 class="custom-title">
			<c:out value='${projet.nom}' />
		</h1>
	</div>
	<div class="row">
		<div class="col-sm">
			<img class="card-img-top"
				src="<c:out value="./images/${projet.image}"/>" alt="Card image cap">
		</div>
		<div class="col-sm">
			<h2>Description du projet</h2>
			<p>
				<c:out value="${projet.descriptif}" />
			</p>
			<p>
				<label>Date de fin des dépôt de candidature :</label>
				<c:out value="${projet.deadLineCandidature}" />
			</p>
			<p>
				<label>Date de fin du projet :</label>
				<c:out value="${projet.deadLineProjet}" />
			</p>
			<p>
				<label>Nombre de candidat maximum :</label>
				<c:out value="${projet.nbMaxCandidatures}" />
			</p>
			<%
				Projet projet = (Projet) request.getAttribute("projet");
				ProjetFactory projetFactory = new ProjetFactoryImpl();
				if (projetFactory.stillCandidaturePlace(projet.getNom())) {
			%>
			<form name="postuler" method="get" action="creationCandidature">
				<input type="hidden" name="projet"
					value="<c:out value="${projet.nom}"/>"> <input
					type="submit" class="btn postuler-btn" value="Postuler" />
			</form>
			<%
				} else {
			%>
			<span class="btn postuler-btn disabled">Plus de place
				disponnible</span>
			<%
				}
			%>
		</div>
	</div>
</div>
<%@include file="./footer.jspf"%>
