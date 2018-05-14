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

<div class="container">
	<x:forEach var="candidat" select="$doc/projets/projet[idProjet=$param:projet]/candidatures">
		<div class="row">
			<div class="col-sm">
					<p>
					Soumis le :
					<x:out select="$pageScope:candidat/candidature/dateCandidature" />
					</p>		
		
					<p>
					Raison social du candidat :
					<x:out select="$pageScope:candidat/candidature/structure/raisonSocial" />
					</p>
			</div>
		</div>			
	</x:forEach>

</div>
<%@include file="./footer.jspf"%>