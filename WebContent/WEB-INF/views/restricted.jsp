<%@include file="./header.jspf"%>

<%@include file="./navigation-common.jspf"%>

<div class="container">
	<p>Vous êtes connecté(e) avec l'adresse
		${sessionScope.sessionUtilisateur.email}, vous avez bien accès à
		l'espace restreint.</p>
</div>

<%@include file="./footer.jspf"%>