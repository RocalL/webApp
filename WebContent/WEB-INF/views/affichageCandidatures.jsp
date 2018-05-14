<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>
<%@ page import="model.*"%>

 <c:set var="projet" value="${requestScope['projet']}" scope="page" />
 <c:out value="${projet}" />

<div class="container">
	<c:forEach var="candidat" items="${projet.candidatures.getCandidature()}">
		<div class="row">
			<div class="col-sm">
					<p>
					Soumis le :
					<c:out value="${candidat.dateCandidature}" />
					</p>		
		
					<p>
					Raison social du candidat :
					<c:out value="${candidat.structure.raisonSocial}" />
					</p>
			</div>
		</div>			
	</c:forEach>
</div>
<%@include file="./footer.jspf"%>