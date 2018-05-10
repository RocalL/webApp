<%@ page pageEncoding="UTF-8"%>
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
	
	<p>
		<b>Liste de tous les projets :</b>
	</p>
	<div>
		<ul>
			<%-- Parcours du document parsé pour y récupérer chaque nœud "projet". --%>
			<x:forEach var="projet" select="$doc/projets/projet">
				<%-- Affichage du descriptif du projet récupéré. --%>
				<li><x:out select="$projet/nom" /></li>
				<ul><li><x:out select="$projet/descriptif" /></li></ul>
			</x:forEach>
		</ul>
	</div>
</div>

<%@include file="./footer.jspf"%>