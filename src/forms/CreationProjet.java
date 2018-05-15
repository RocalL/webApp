package forms;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Candidature;
import model.Projet;
import model.RepProjet;
import model.Structure;
import model.Utilisateur;
import services.Projets;
import util.JaxParser;

public class CreationProjet {
	private static final String CHAMP_NOM = "nomProjet";
	private static final String CHAMP_DESCRIPTION = "description";
	private static final String CHAMP_DEADLINE_CANDIDATURE = "deadLineCandidature";
	private static final String CHAMP_DEADLINE_PROJET = "deadLineProjet";
	private static final String CHAMP_NOMBRE_DEV = "nombreDev";
	private static final String CHAMP_IMAGE = "Image";

	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Projet creerProjet(HttpServletRequest request, String chemin) {
		HttpSession session = request.getSession();
		//Utilisateur utilisateur = (Utilisateur) session.getAttribute(ATT_SESSION_USER);

		String nomProjet = getValeurChamp(request, CHAMP_NOM);
		String description = getValeurChamp(request, CHAMP_DESCRIPTION);
		String deadLineCandidature = getValeurChamp(request, CHAMP_DEADLINE_CANDIDATURE);
		String deadLineProjet = getValeurChamp(request, CHAMP_DEADLINE_PROJET);
		String nombreDev = getValeurChamp(request, CHAMP_NOMBRE_DEV);
		String Image = getValeurChamp(request, CHAMP_IMAGE);

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
		
		try {
			validationNombreDev(nombreDev);
		} catch (Exception e) {
			setErreur(CHAMP_NOMBRE_DEV, e.getMessage());
		}
		int nbDev = Integer.parseInt(nombreDev);
		projet.setNbMaxCandidatures(nbDev);
		
		try {
			File file = new File(request.getServletContext().getRealPath(chemin));
			if (erreurs.isEmpty()) {
				// Read
				Projets listProjets = JaxParser.unmarshal(Projets.class, file);
				for (Projet p : listProjets.getProjet()) {
					if (p.getNom().equals(projet.getNom())) {
						throw new Exception();
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
			resultat = "échec de la création de la candidature.";
		}

		return projet;
	}


	private void validationNomProjet(String nomProjet) throws Exception {
		if (nomProjet != null) {
			if (nomProjet.length() < 2) {
				throw new Exception("Le Nom du projet doit au moins être défini par 2 caract�res.");
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
	
	private int validationNombreDev(String nombreDev) throws Exception {
		int temp;
		if (nombreDev != null) {
			try {
				temp = Integer.parseInt(nombreDev);
				if (temp < 1) {
					throw new Exception("Il faut au moins un développeur pour ce projet.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new Exception("Le nombre Max de Développeur doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new Exception("Merci de définir le nombre de développeur maximum.");
		}
		return temp;
	}

	private void validationDeadLineCandidature(String deadLineCandidature) throws Exception {
		if (! isValidDate(deadLineCandidature)) {
			throw new Exception("Veuillez saisir une date limite de candidature valide");
			}
	}

	private void validationDeadLineProjet(String deadLineProjet) throws Exception {
		if (! isValidDate(deadLineProjet)) {
			throw new Exception("Veuillez saisir une date de cloture du projet valide");
			}
	}
	
	public static boolean isValidDate(String inDate) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    dateFormat.setLenient(false);
		    try {
		      dateFormat.parse(inDate.trim());
		    } catch (ParseException pe) {
		      return false;
		    }
		    return true;
		  }

	/*
	 * Ajoute un message correspondant au champ sp�cifi� � la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/*
	 * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
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