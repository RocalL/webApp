<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>
<!-- comment -->
<div class="container">
	<div class="row">
		<h1 class="custom-title">Création d'un Projet</h1>
	</div>
	<div class="row" id="projet">
		<div class="col-lg-12" id="form">
			<form method="post" action="creationProjet">
				<div class="form-group">
						    <label for="nomProjet">Nom du Projet<span class="requis">*</span></label> 
							<input type="text" class="form-control" id="nomProjet" name="nomProjet" placeholder="Ex: Création d'une apllication web">
							<span class="erreur">${form.erreurs['Nom du projet']}</span>
							
							<label for="descriptifProjet">Descriptif<span class="requis">*</span></label>
							<input type="text" class="form-control" id="descriptifProjet" name="descriptifProjet" placeholder="détail du projet">
							<span class="erreur">${form.erreurs['descriptif du projet']}</span>
							
							<label for="deadLineCandidature">Date Limite de candidature<span class="requis">*</span></label>
							<input type="date" class="form-control" id="deadLineCandidature" name="deadLineCandidature" placeholder="01/01/2000"> 
							<span class="erreur">${form.erreurs['date limite de candidature']}</span>
							
							<label for="deadLineProjet">Date de cloture du projet<span class="requis">*</span></label>
							<input type="date" class="form-control" id="deadLineProjet" name="deadLineProjet" placeholder="01/01/2000"> 
							<span class="erreur">${form.erreurs['date de cloture du projet']}</span>
							
							<label for="nbMaxCandidatures">Nombre de développeur(s) nécessaire(s)<span class="requis">*</span></label>
							<input type="number" class="form-control" id="deadLineProjet" name="deadLineProjet" placeholder="01"> 
							<span class="erreur">${form.erreurs['Nombre de développeur']}</span>
							
							<label for="ImageProjet">Image d'illustration</label>
							<div class="input1" id="ImageProjet">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="fichiers" name="fichiers">
									<label class="custom-file-label" for="fichiers">Charger une image</label>															
								</div>
							</div>
							<small id="fichierWarn" class="form-text text-muted">veuillez importer une image valide</small>				
					</div>
					<div id="sendButton">
						<button type="submit" class="btn">Soumettre le projet</button>
					</div>
					<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
			</form>
		</div>
</div>
</div>
<%@include file="./footer.jspf"%>
