<%@page import="forms.CreationProjetForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="./header.jspf"%>

<%@include file="./navigation.jspf"%>
<!-- comment -->
<div class="container">
	<div class="row">
		<h1 class="custom-title">Création d'un Projet </h1>
	</div>
	<div class="row" id="projet">
		<div class="col-lg-12" id="form">
			<form method="post" action="creationProjet" enctype='multipart/form-data'>
				<div class="form-group">
						    <label for="nomProjet">Nom du Projet<span class="requis">*</span></label> 
							<input type="text" class="form-control" id="nomProjet" name="nomProjet" placeholder="Ex: Création d'une apllication web">
							<span class="erreur">${form.erreurs['nomProjet']}</span>
							
							<label for="descriptifProjet">Descriptif<span class="requis">*</span></label>
							<input type="text" class="form-control" id="descriptifProjet" name="descriptifProjet" placeholder="détail du projet">
							<span class="erreur">${form.erreurs['descriptifProjet']}</span>
							
							<label for="deadLineCandidature">Date Limite de candidature<span class="requis">*</span></label>
							<input type="date" class="form-control" id="deadLineCandidature" name="deadLineCandidature" placeholder="jj/mm/aaaa" required pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}"> 
							<span class="erreur">${form.erreurs['deadLineCandidature']}</span>
							
							<label for="deadLineProjet">Date de cloture du projet<span class="requis">*</span></label>
							<input type="date" class="form-control" id="deadLineProjet" name="deadLineProjet" placeholder="jj/mm/aaaa" required pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}"> 
							<span class="erreur">${form.erreurs['deadLineProjet']}</span>
							
							<label for="nbMaxCandidatures">Nombre de candidats maximum<span class="requis">*</span></label>
							<input type="number" class="form-control" id="nbMaxCandidatures" name="nbMaxCandidatures" placeholder="01"> 
							<span class="erreur">${form.erreurs['nbMaxCandidatures']}</span>
							
							<label for="ImageProjet">Image d'illustration</label>
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="imageProjet" name="imageProjet" >
									<label class="custom-file-label" for="imageProjet">Charger une image</label>															
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
