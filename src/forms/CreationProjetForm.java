package forms;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import model.Projet;
import model.Candidatures;
import model.Projets;
import util.JaxParser;

public class CreationProjetForm {
	private static final String CHAMP_NOM = "nomProjet";
	private static final String CHAMP_DESCRIPTION = "descriptifProjet";
	private static final String CHAMP_DEADLINE_CANDIDATURE = "deadLineCandidature";
	private static final String CHAMP_DEADLINE_PROJET = "deadLineProjet";
	private static final String CHAMP_NB_MAX_CANDIDATURE = "nbMaxCandidatures";
	private static final String CHAMP_IMAGE = "imageProjet";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}
	public Projet creerProjet(HttpServletRequest request, String chemin) {

		String nomProjet = getValeurChamp(request, CHAMP_NOM);
		String description = getValeurChamp(request, CHAMP_DESCRIPTION);
		String deadLineCandidature = getValeurChamp(request, CHAMP_DEADLINE_CANDIDATURE);
		String deadLineProjet = getValeurChamp(request, CHAMP_DEADLINE_PROJET);
		String nbMaxCandidatures = getValeurChamp(request, CHAMP_NB_MAX_CANDIDATURE);
		String image = getValeurChamp(request, CHAMP_IMAGE);

		Projet projet = new Projet();
		Date date = new Date();
		

		try {
			validationNomProjet(nomProjet);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		projet.setNom(nomProjet);

		try {
			validationDescription(description);
		} catch (Exception e) {
			setErreur(CHAMP_DESCRIPTION, e.getMessage());
		}
		projet.setDescriptif(description);

		try {
			validationDeadLineCandidature(deadLineCandidature);
		} catch (Exception e) {
			setErreur(CHAMP_DEADLINE_CANDIDATURE, e.getMessage());
		}
		projet.setDeadLineCandidature(deadLineCandidature);

		try {
			validationDeadLineProjet(deadLineProjet);
		} catch (Exception e) {
			setErreur(CHAMP_DEADLINE_PROJET, e.getMessage());
		}
		projet.setDeadLineProjet(deadLineProjet);

		int nbMaxCandidatureInt = -1;
		try {
			nbMaxCandidatureInt = validationNbMaxCandidature(nbMaxCandidatures);
		} catch (Exception e) {
			setErreur(CHAMP_NB_MAX_CANDIDATURE, e.getMessage());
		}
		projet.setNbMaxCandidatures(nbMaxCandidatureInt);
		projet.setImage("./resources/img/default.jpg");
		projet.setCandidatures(new Candidatures());

		try {
			File file = new File(chemin);
			if (erreurs.isEmpty()) {
				// Read
				Projets listProjets = JaxParser.unmarshal(Projets.class, file);
				for (Projet p : listProjets.getProjet()) {
					System.out.println(p.getNom() +"_"+ projet.getNom());
					if (p.getNom().equals(projet.getNom())) {
						resultat = "échec de la création de la candidature : un projet portant ce nom existe déjà";
					}
				}
				listProjets.getProjet().add(projet);
				// Write
				JaxParser.marshal(listProjets, file);
				System.out.println(projet);
				System.out.println("ajoutée à la base de donnée");
			}
		} catch (Exception e) {
			setErreur("ParserError", e.getMessage());
			e.printStackTrace();
		}

		if (erreurs.isEmpty()) {
			resultat = "Succès de la création du projet le " + date.toString();
		} else {
			resultat = "échec de la création de la projet.";
		}

		return projet;
	}

	private void validationNomProjet(String nomProjet) throws Exception {
		if (nomProjet != null) {
			if (nomProjet.length() < 2) {
				throw new Exception("Le nom du projet doit au moins être défini par 2 caractères.");
			}
		} else {
			throw new Exception("Merci de donner un nom au projet.");
		}
	}

	private void validationDescription(String description) throws Exception {
		if (description != null) {
			if (description.length() < 10) {
				throw new Exception("Veuillez au moins saisir une phrase pour décrire le projet");
			}
		} else {
			throw new Exception("Veuillez décrire le projet avec plus de détail.");
		}
	}

	private int validationNbMaxCandidature(String nbMaxCandidature) throws Exception {
		int temp;
		if (nbMaxCandidature != null) {
			try {
				temp = Integer.parseInt(nbMaxCandidature);
				if (temp < 1) {
					throw new Exception("Il faut au moins un candidat pour ce projet.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new Exception("La valeur maximum de candidat doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new Exception("Merci de définir le nombre de candidat maximum.");
		}
		return temp;
	}

	private void validationDeadLineCandidature(String deadLineCandidature) throws Exception {
		if (!isValidDate(deadLineCandidature)) {
			throw new Exception("Veuillez saisir une date limite de candidature valide");
		}
	}

	private void validationDeadLineProjet(String deadLineProjet) throws Exception {
		if (!isValidDate(deadLineProjet)) {
			throw new Exception("Veuillez saisir une date de cloture du projet valide");
		}
	}

	public static boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/*
	 * Mêthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}