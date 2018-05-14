<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>
<%-- Récupération du document XML. --%>
<c:import url="../database/projets.xml" var="documentXML" />
<%-- Analyse du document XML récupéré. --%>
<x:parse var="doc" doc="${documentXML}" />

<%-- Enregistre le résultat de l'expression XPath, spécifiée dans l'attribut select, 
dans une variable de session nommée 'auteur' --%>
<x:set var="projet" scope="page"
	select="$doc/projets/projet[idProjet=$param:projet]"></x:set>



<div class="container">
	<div class="row">
		<h1 class="custom-title">
			<x:out select="$pageScope:projet/nom" />
		</h1>

	</div>
	<div class="row">
		<div class="col-sm">
			<h2>Liste des candidatures</h2>
			<p>
				Date de fin des dépôt de candidature :
				<x:out select="$pageScope:projet/deadLineCandidature" />
			</p>
			<p>
				Date de fin du projet :
				<x:out select="$pageScope:projet/deadLineProjet" />
			</p>
			<p>
				Nombre de candidat maximum :
				<x:out select="$pageScope:projet/nbMaxCandidatures" />
			</p>
			<form name="postuler" method="get" action="creationCandidature">
				<input type="hidden" name="projet"
					value="<x:out select='$projet/idProjet'/>"> <input
					type="submit" class="btn postuler-btn" value="Postuler" />
			</form>

		</div>
	</div>
</div>
<%@include file="./footer.jspf"%>