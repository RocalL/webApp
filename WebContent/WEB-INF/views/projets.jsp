<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>

<div class="container">

	<%-- Récupération du document XML. --%>
	<c:import url="../database/projets.xml" var="documentXML" />
	<%-- Analyse du document XML récupéré. --%>
	<x:parse var="doc" doc="${documentXML}" />
	<div class="row">
		<h1 class="custom-title">Liste de tous les projets</h1>
	</div>
	<div class="addProjet">
		<button onclick='location.href=
			"creationProjet"' class="btn">
			<i class="fas fa-plus-square"></i>
		</button>
	</div>
	<div class="row">
		<div>
			<div class="row">
				<x:forEach var="projet" select="$doc/projets/projet">
					<div class="col-sm-3">
						<div class="card">
							<img class="card-img-top"
								src="<x:out
									select="$projet/image"/>"
								alt="Card image cap">
								<form name="details" method="get" action="affichageCandidatures">
									<input type="hidden" name="projet"
										value="<x:out select='$projet/idProjet'/>"> <input
										type="submit" class="btn card-btn" value="Candidatures" />
								</form>
								
							<div class="card-body">
								<h5 class="card-title">
									<x:out select="$projet/nom" />
								</h5>
								<div class="line"></div>
								<p class="card-text">
									<x:out select="$projet/descriptif" />
								</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">Date limite de candidature : <x:out
										select="$projet/deadLineProjet" /></small>
								<form name="details" method="get" action="affichageProjet">
									<input type="hidden" name="projet"
										value="<x:out select='$projet/idProjet'/>"> <input
										type="submit" class="btn card-btn" value="Consulter" />
								</form>
							</div>
						</div>
					</div>

				</x:forEach>
			</div>
		</div>
	</div>
</div>

<%@include file="./footer.jspf"%>