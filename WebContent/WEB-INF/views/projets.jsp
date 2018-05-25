<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>
<%@ page import="model.Projets"%>
<%@ page import="model.Projet"%>

<div class="container">
	<c:set var="projets" value="${requestScope['projets']}" scope="page" />
	<div class="row">
		<h1 class="custom-title">Liste de tous les projets</h1>
	</div>
	<c:if test = "${sessionScope.sessionUtilisateur.role == 'admin'}">
	<div class="addProjet">
		<button onclick='location.href=
			"creationProjet"' class="btn">
			<i class="fas fa-plus"></i>
		</button>
	</div>
	</c:if>
	<div class="row">
		<div>
			<div class="row">
				<c:forEach var="projet" items="${projets.getProjet()}">
					<div class="col-sm-3">
						<div class="card">
							<img class="card-img-top"
								src="<c:out value="./images/${projet.image}"/>" alt="Card image cap">
								<c:if test = "${sessionScope.sessionUtilisateur.role == 'admin'}">
							<form name="details" method="get" action="affichageCandidatures">
								<input type="hidden" name="projet" value="${projet.nom}"> <input
									type="submit" class="btn btn-custom card-btn" value="Candidatures" />
							</form>
							</c:if>
							<div class="card-body">
								<h5 class="card-title">
									<c:out value="${projet.nom}" />
								</h5>
								<div class="line"></div>
								<p class="card-text">
									<c:out value="${projet.descriptif}" />
								</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">Date limite de candidature : <c:out
										value="${projet.deadLineProjet}" /></small>
								<form name="details" method="get" action="affichageProjet">
									<input type="hidden" name="projet"
										value="<c:out value='${projet.nom}'/>"> <input
										type="submit" class="btn card-btn" value="Consulter" />
								</form>
							</div>
						</div>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>
</div>
<%@include file="./footer.jspf"%>