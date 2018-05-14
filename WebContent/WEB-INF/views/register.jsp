
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>

<div class="container">
	<form method="post" action="register">
		<fieldset>
			<legend>Inscription</legend>
			<p>Vous pouvez vous inscrire via ce formulaire.</p>
			
			<label for="prenom">Prénom <span class="requis">*</span></label>
			<input type="text" id="prenom" name="prenom"
				value ="<c:out value="${utilisateur.prenom}"/>" size="20"
				maxlength="20" /> <span class="erreur">${form.erreurs['prenom']}</span>
			<label for="nom">Nom <span class="requis">*</span></label> 
			<input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="20"
				maxlength="20" />
			<span class="erreur">${form.erreurs['nom']}</span>
			
			<label for="tel">Téléphone <span class="requis">*</span></label>
			<input type="text" id="tel" name="tel"
				value ="<c:out value="${utilisateur.tel}"/>" size="10"
				maxlength="20" /> <span class="erreur">${form.erreurs['tel']}</span>

			<label for="email">Adresse email <span class="requis">*</span></label>
			<input type="email" id="email" name="email"
				value="<c:out value="${utilisateur.email}"/>" size="20"
				maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>
			<br /> 
			<label for="motdepasse">Mot de passe <span class="requis">*</span></label> 
			<input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" /> 
			<span class="erreur">${form.erreurs['motdepasse']}</span> <br /> 
			<label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
			<input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
			<span class="erreur">${form.erreurs['confirmation']}</span>
			<br/>
			<input type="submit" value="Inscription" class="sansLabel" />
			<br />

			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
		</fieldset>
	</form>
</div>

<%@include file="./footer.jspf"%>