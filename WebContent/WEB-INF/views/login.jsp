<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>

<div class="container">
	<form method="post" action="login">
		<fieldset>
			<legend>Connexion</legend>
			<p>Vous pouvez vous connecter via ce formulaire.</p>

			<label for="nom">Adresse email <span class="requis">*</span></label>
			<input type="email" id="email" name="email"
				value="<c:out value="${utilisateur.email}"/>" size="20"
				maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>


			<label for="motdepasse">Mot de passe <span class="requis">*</span></label>
			<input type="password" id="motdepasse" name="motdepasse" value=""
				size="20" maxlength="20" /> <span class="erreur">${form.erreurs['motdepasse']}</span>


			<input type="submit" value="Connexion" class="sansLabel btn" />


			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
			<span class="erreur">${form.erreurs['wrongCredentials']}</span>

			<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:if test="${!empty sessionScope.sessionUtilisateur}">
				<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
				<p class="succes">Vous êtes connecté(e) avec l'adresse :
					${sessionScope.sessionUtilisateur.email}</p>
			</c:if>
		</fieldset>
	</form>
	<fieldset>
		<form action="register">
			<input type="submit" value="S'enregistrer" class="sansLabel btn">
		</form>
	</fieldset>
</div>

<%@include file="./footer.jspf"%>