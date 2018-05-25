<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>
<%@ page import="model.Projet"%>
<%@ page import="model.Candidature"%>


<div class="container">
	<div class="row">
		<div class="col-sm">
			<h2>Description de la candidature</h2>
			<p>
				<c:out value="${candidature}" />
			</p>
			
			<p>
				<c:out value="${candidature.etatCandidature}" />
			</p>
			<p>
				<c:out value="${candidature.utilisateur.email}" />
			</p>
			<p>
				<c:out value="${candidature.utilisateur.nom}" />
			</p>
			<p>
				<c:out value="${candidature.utilisateur.prenom}" />
			</p>
			<p>
				<c:out value="${candidature.utilisateur.tel}" />
			</p>
			<p>
				<c:out value="${candidature.structure.raisonSocial}" />
			</p>
			<p>
				<c:out value="${candidature.structure.siret}" />
			</p>
			<p>
				<c:out value="${candidature.structure.ca}" />
			</p>
			<p>
				<c:out value="${candidature.repProjet.delaisPropose}" />
			</p>
			<p>
				<c:out value="${candidature.repProjet.devis}" />
			</p>
			<p>
				<c:out value="${candidature.repProjet.website}" />
			</p>
			<p>
				<c:out value="${candidature.repProjet.fichier}" />
			</p>
			<form action="validateCandidature" method = "get" class="candidatureBtn">
				<button class="btn" title="Valider candidature">
					<i class="fas fa-check"></i>
				</button>
				<input type="hidden" name="utilisateur" value="${candidature.utilisateur.email}">
				<input type="hidden" name="projet" value="${projet.nom}">
			</form>							
			<form action="deleteCandidature" method = "get" class="candidatureBtn">
				<button class="btn" title="Supprimer candidature">
					<i class="fas fa-trash"></i>
				</button>
				<input type="hidden" name="utilisateur" value="${candidature.utilisateur.email}">
				<input type="hidden" name="projet" value="${projet.nom}">
			</form>	
		</div>
	</div>
</div>
<%@include file="./footer.jspf"%>