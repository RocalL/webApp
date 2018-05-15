<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>

<div class="container">
	<div class="row">
		<h1 class="custom-title">Création d'une candidature</h1>
	</div>
	<div class="row" id="candidature">
		<div class="col-lg-12" id="form">
			<form method="post" action="creationCandidature">
				<div class="form-group">
					<div class="row">
						<div class="col-lg-4" id="panneauGauche">
							<label for="nomDuCandidat">Nom <span class="requis">*</span></label> 
							<input type="text" class="form-control" id="nomDuCandidat" name="nomDuCandidat" placeholder="Nom" value="${sessionScope.sessionUtilisateur.nom}" disabled>
							<span class="erreur">${form.erreurs['nom']}</span>
							
							<label for="prenomDuCandidat">Prénom <span class="requis">*</span></label>
							<input type="text" class="form-control" id="prenomDuCandidat" name="prenomDuCandidat" placeholder="Prénom" value="${sessionScope.sessionUtilisateur.prenom}" disabled>
							<span class="erreur">${form.erreurs['prenom']}</span>
							
							<label for="numTel">Numéro de téléphone <span class="requis">*</span></label>
							<input type="number" class="form-control" id="numTel" name="numTel" placeholder="0123456789" value="${sessionScope.sessionUtilisateur.tel}" disabled> 
							<span class="erreur">${form.erreurs['tel']}</span>
							
							<label for="mail">Mail <span class="requis">*</span></label>
							<input type="email" class="form-control" id="mail" name="mail" aria-describedby="emailWarn" placeholder="Mail" value="${sessionScope.sessionUtilisateur.email}" disabled>
							<span class="erreur">${form.erreurs['mail']}</span>
							<small id="emailWarn" class="form-text text-muted">Nous nous engageons à vendre cet e-mail au plus offrant</small>
						</div>
						<div class="col-lg-4" id="panneaumilieu">
							<label for="raisonSocial">Raison sociale <span class="requis">*</span></label>
							<input type="text" class="form-control" id="raisonSociale" name="raisonSociale" placeholder="Raison sociale">
							<span class="erreur">${form.erreurs['raisonSociale']}</span>
							
							<label for="siret">Siret <span class="requis">*</span></label>
							<input type="number" class="form-control" id="siret" name="siret" placeholder="Votre numéro de siret à 14 chiffres" maxlength="14">
							<span class="erreur">${form.erreurs['siret']}</span>
							
							<label for="ca">Chiffre d'affaire <span class="requis">*</span></label> 
							<input type="number" class="form-control" id="ca" name="ca" placeholder="Chiffre d'affaire annuel en €">
							<span class="erreur">${form.erreurs['ca']}</span>
							
							<label for="url">Site internet </label>
							<input type="text" class="form-control" id="website" name="website" placeholder="Adresse de votre site internet">
							<span class="erreur">${form.erreurs['website']}</span>
						</div>
						<div class="col-lg-4" id="panneauDroite">
							<label for="url">Délai proposé <span class="requis">*</span></label>
							<input type="text" class="form-control" id="delai" name="delai" placeholder="En nombre de journées">
							<span class="erreur">${form.erreurs['delai']}</span>
							
							<label for="devis">Devis <span class="requis">*</span></label> 
							<div class="input-group mb-2" id="devis">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="devis" name="devis">
									<span class="erreur">${form.erreurs['devis']}</span>
									<label class="custom-file-label" for="devis">Importer votre devis</label>
								</div>
							</div>
							<label for="fichierComplementaires">Fichiers
								complémentaires</label>
							<div class="input-group mb-2" id="fichierComplementaires">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="fichiers" name="fichiers">
									<label class="custom-file-label" for="fichiers">Fichiers</label>															
								</div>
							</div>
							<small id="fichierWarn" class="form-text text-muted">Utilisez le format .zip pour importer plusieurs fichiers</small>
						</div>
						<input type="hidden" name="projet" value="${param.projet}">
					</div>
					<div id="sendButton">
						<button type="submit" class="btn">Envoyer ma candidature</button>
					</div>
					<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
				</div>
			</form>

		</div>
	</div>
</div>
<%@include file="./footer.jspf"%>