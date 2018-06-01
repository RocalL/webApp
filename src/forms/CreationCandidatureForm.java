package forms;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import exception.FactoryException;
import exception.FormValidationException;
import factory.CandidatureFactory;
import factory.ProjetFactory;
import model.Candidature;
import model.Projet;
import model.RepProjet;
import model.Structure;
import model.Utilisateur;

public final class CreationCandidatureForm {
	private static final String CHAMP_RAISONSOCIALE = "raisonSociale";
	private static final String CHAMP_SIRET = "siret";
	private static final String CHAMP_CA = "ca";
	private static final String CHAMP_WEBSITE = "website";
	private static final String CHAMP_DEVIS = "devis";
	private static final String CHAMP_DELAI = "delai";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private CandidatureFactory candidatureFactory;
	private ProjetFactory projetFactory;

	public CreationCandidatureForm(CandidatureFactory candidatureFactory, ProjetFactory projetFactory) {
		this.candidatureFactory = candidatureFactory;
		this.projetFactory = projetFactory;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Candidature creerCandidature(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String raisonSociale = getValeurChamp(request, CHAMP_RAISONSOCIALE);
		String siret = getValeurChamp(request, CHAMP_SIRET);
		String ca = getValeurChamp(request, CHAMP_CA);
		String delai = getValeurChamp(request, CHAMP_DELAI);
		String website = getValeurChamp(request, CHAMP_WEBSITE);
		String devis = getValeurChamp(request, CHAMP_DEVIS);

		Candidature candidature = new Candidature();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
		Structure structure = new Structure();
		RepProjet repProjet = new RepProjet();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		Date date = new Date();

		traiterRaisonSociale(raisonSociale, structure);
		traiterSiret(siret, structure);
		traiterCa(ca, structure);
		traiterDelai(delai, repProjet);
		traiterDevis(devis, repProjet);
		traiterWebsite(website, repProjet);

		candidature.setUtilisateur(utilisateur);
		candidature.setStructure(structure);
		candidature.setRepProjet(repProjet);
		candidature.setDateCandidature(dateFormat.format(date));
		candidature.setEtatCandidature("En attente");

		try {
			if (erreurs.isEmpty()) {
				Projet projet = projetFactory.getOne(request.getParameter("projet"));
				candidatureFactory.create(candidature, projet);
				resultat = "Succès de la création de la candidature.";
			} else {
				resultat = "Échec de la création de la candidature.";
			}
		} catch (FactoryException e) {
			setErreur("imprévu", "Erreur imprévue lors de la création.");
			resultat = "Échec de la création de la candidature : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return candidature;
	}

	private void traiterRaisonSociale(String raisonSociale, Structure structure) {
		try {
			validationRaisonSociale(raisonSociale);
		} catch (FormValidationException e) {
			setErreur(CHAMP_RAISONSOCIALE, e.getMessage());
		}
		structure.setRaisonSocial(raisonSociale);
	}

	private void traiterSiret(String siret, Structure structure) {
		long siretLong = -1;
		try {
			siretLong = validationSiret(siret);
		} catch (FormValidationException e) {
			setErreur(CHAMP_SIRET, e.getMessage());
		}
		structure.setSiret(siretLong);
	}

	private void traiterCa(String ca, Structure structure) {
		int caInt = -1;
		try {
			caInt = validationCa(ca);
		} catch (FormValidationException e) {
			setErreur(CHAMP_CA, e.getMessage());
		}
		structure.setCa(caInt);
	}

	private void traiterDelai(String delai, RepProjet repProjet) {
		int delaiInt = -1;
		try {
			delaiInt = validationDelai(delai);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DELAI, e.getMessage());
		}
		repProjet.setDelaisPropose(delaiInt);
	}

	private void traiterDevis(String devis, RepProjet repProjet) {
		try {
			validationDevis(devis);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DEVIS, e.getMessage());
		}
		repProjet.setDevis(devis);
	}

	private void traiterWebsite(String website, RepProjet repProjet) {
		if (website == null) {
			website = "Aucun site web";
		}
		repProjet.setWebsite(website);
	}

	private void validationRaisonSociale(String raisonSociale) throws FormValidationException {
		if (raisonSociale != null) {
			if (raisonSociale.length() < 2) {
				throw new FormValidationException("Le champ raison sociale doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer une raison sociale.");
		}
	}

	private long validationSiret(String siret) throws FormValidationException {
		long temp;
		if (siret != null) {
			try {
				temp = Long.parseLong(siret);
				if (siret.length() < 1) { // 14
					throw new FormValidationException("Le siret doit contenir au moins 14 caractères.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new FormValidationException("Le siret doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new FormValidationException("Merci d'entrer un siret.");
		}
		return temp;
	}

	private int validationCa(String ca) throws FormValidationException {
		int temp;
		if (ca != null) {
			try {
				temp = Integer.parseInt(ca);
				if (temp < 0) {
					throw new FormValidationException("Le chiffre d'affaire doit être un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new FormValidationException("Le chiffre d'affaire doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new FormValidationException("Merci d'entrer un chiffre d'affaire.");
		}
		return temp;
	}

	private int validationDelai(String delai) throws FormValidationException {
		int temp;
		if (delai != null) {
			try {
				temp = Integer.parseInt(delai);
				if (temp < 0) {
					throw new FormValidationException("Le délai doit être un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new FormValidationException("Le délai doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new FormValidationException("Merci d'entrer un délai.");
		}
		return temp;
	}

	private void validationDevis(String devis) throws FormValidationException {
		if (devis != null) {
			if (devis.length() < 2) {
				throw new FormValidationException("Le champ devis doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un devis.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
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