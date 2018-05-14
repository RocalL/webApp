package forms;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Candidature;
import model.RepProjet;
import model.Structure;
import model.Utilisateur;
import services.Projets;
import util.JaxParser;

public class CreationCandidatureForm {
	private static final String CHAMP_RAISONSOCIALE = "raisonSociale";
	private static final String CHAMP_SIRET = "siret";
	private static final String CHAMP_CA = "ca";
	private static final String CHAMP_WEBSITE = "website";
	private static final String CHAMP_DEVIS = "devis";
	private static final String CHAMP_FICHIERS = "fichiers";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Candidature creerCandidature(HttpServletRequest request, String chemin) {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute(ATT_SESSION_USER);

		String raisonSociale = getValeurChamp(request, CHAMP_RAISONSOCIALE);
		String siret = getValeurChamp(request, CHAMP_SIRET);
		String ca = getValeurChamp(request, CHAMP_CA);

		String website = getValeurChamp(request, CHAMP_WEBSITE);
		String devis = getValeurChamp(request, CHAMP_DEVIS);
		String fichiers = getValeurChamp(request, CHAMP_FICHIERS);

		Candidature candidature = new Candidature();

		Structure structure = new Structure();
		RepProjet repProjet = new RepProjet();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		Date date = new Date();

		try {
			validationRaisonSociale(raisonSociale);
		} catch (Exception e) {
			setErreur(CHAMP_RAISONSOCIALE, e.getMessage());
		}
		structure.setRaisonSocial(raisonSociale);

		long siretLong = -1;
		try {
			siretLong = validationSiret(siret);
		} catch (Exception e) {
			setErreur(CHAMP_SIRET, e.getMessage());
		}
		structure.setSiret(siretLong);

		int caInt = -1;
		try {
			caInt = validationCa(ca);
		} catch (Exception e) {
			setErreur(CHAMP_CA, e.getMessage());
		}
		structure.setCa(caInt);

		candidature.setUtilisateur(utilisateur);
		candidature.setStructure(structure);
		candidature.setRepProjet(repProjet);
		candidature.setDateCandidature(dateFormat.format(date));

		try {
			if (erreurs.isEmpty()) {
				// Read
				Projets listProjets = JaxParser.unmarshal(Projets.class, new File(request.getServletContext().getRealPath(chemin)));
				
			}
		} catch (Exception e) {
			setErreur("ParserError", e.getMessage());
			e.printStackTrace();
		}

		if (erreurs.isEmpty()) {
			resultat = "Succès de la création de la candidature le " + date.toString();
		} else {
			resultat = "échec de la création de la candidature.";
		}

		return candidature;
	}

	private void validationRaisonSociale(String raisonSociale) throws Exception {
		if (raisonSociale != null) {
			if (raisonSociale.length() < 2) {
				throw new Exception("Le champ raison sociale doit contenir au moins 2 caract�res.");
			}
		} else {
			throw new Exception("Merci d'entrer une raison sociale.");
		}
	}

	private long validationSiret(String siret) throws Exception {
		long temp;
		if (siret != null) {
			try {
				temp = Long.parseLong(siret);
				if (siret.length() < 1) { // 14
					throw new Exception("Le siret doit contenir au moins 14 caract�res.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new Exception("Le siret doit �tre un nombre.");
			}
		} else {
			temp = -1;
			throw new Exception("Merci d'entrer un siret.");
		}
		return temp;
	}

	private int validationCa(String ca) throws Exception {
		int temp;
		if (ca != null) {
			try {
				temp = Integer.parseInt(ca);
				if (temp < 0) {
					throw new Exception("Le chiffre d'affaire doit �tre un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new Exception("Le chiffre d'affaire doit �tre un nombre.");
			}
		} else {
			temp = -1;
			throw new Exception("Merci d'entrer un chiffre d'affaire.");
		}
		return temp;
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
