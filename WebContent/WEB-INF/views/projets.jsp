<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>

<div class="container">
	<p>Vous êtes connecté(e) avec l'adresse
		${sessionScope.sessionUtilisateur.email}, vous avez bien accès à
		l'espace restreint.</p>

	<%-- Récupération du document XML. --%>
	<c:import url="../database/projets.xml" var="documentXML" />
	<%-- Analyse du document XML récupéré. --%>
	<x:parse var="doc" doc="${documentXML}" />
	<h1 class="projet-title">
		<b>Liste de tous les projets</b>
	</h1>
	<a href="creationProjet" class="btn btn-primary">Créer un nouveau
		projet</a>
	<div>
		<div class="row">
			<x:forEach var="projet" select="$doc/projets/projet">
				<div class="col-sm-3">
					<div class="card">
						<img class="card-img-top"
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0_2nqm0H20gpO-Pf9BsBwuAYt3McWcb-6rFs37i244h71Lyrnkg"
							alt="Card image cap">
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
							<small class="text-muted"><x:out
									select="$projet/deadLineProjet" /></small> <a href="#"
								class="btn btn-primary">Détails</a>
						</div>
					</div>
				</div>

			</x:forEach>
		</div>
	</div>
</div>

<%@include file="./footer.jspf"%>