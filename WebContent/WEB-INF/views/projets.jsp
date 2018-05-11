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
	<h1 class="projet-title">
		<b>Liste de tous les projets</b>
	</h1>
	<div class="addProjet">
		<button onclick='location.href=
			"creationProjet"' class="btn btn-primary">
			<span class="glyphicon glyphicon-plus"></span>
		</button>
	</div>
	<div>
		<div class="row">
			<x:forEach var="projet" select="$doc/projets/projet">
				<div class="col-sm-3">
					<div class="card">
						<img class="card-img-top"
							src="<x:out
									select="$projet/image"/>"
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
							<small class="text-muted">Date limite de candidature : <x:out
									select="$projet/deadLineProjet" /></small> <a href="#"
								class="btn btn-primary card-btn">Consulter</a>
						</div>
					</div>
				</div>

			</x:forEach>
		</div>
	</div>
</div>

<%@include file="./footer.jspf"%>