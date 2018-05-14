<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>
<%@ page import="model.Projet"%>

<c:set var="projet" value="${requestScope['projet']}" scope="page" />

<div class="container">
	<div class="row">
		<h1 class="custom-title">Liste des candidatures pour le projet :</h1>
		<h2 class="custom-title">
			"
			<c:out value='${projet.nom}' />
			"
		</h2>
	</div>
	<div class="row">
		<div class="col-sm">
			<table>
				<thead>
					<th>Soumis le :</th>
					<th>Entreprise :</th>
					<th>Délai proposé en jours :</th>
					<th></th>
				</thead>

				<tbody>
					<tr>
						<c:forEach var="candidature"
							items="${projet.getCandidatures().getCandidature()}">
							<tr>
								<td><c:out value="${candidature.dateCandidature}" /></td>
								<td><c:out value="${candidature.structure.raisonSocial}" /></td>
								<td><c:out value="${candidature.repProjet.delaisPropose}" /></td>
								<td>
									<button onclick='location.href=""'class="btn">
										<i class="fas fa-check"></i>
									</button>
									<button onclick='location.href=""'class="btn">
										<i class="fas fa-trash"></i>
									</button>
									<button onclick='location.href=""'class="btn">
										<i class="far fa-eye"></i>
									</button>
								</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@include file="./footer.jspf"%>