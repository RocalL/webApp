package forms;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import model.Utilisateur;
import model.Utilisateurs;
import util.Pbkdf2;
/**
 * <b>Classe qui récupère les informations envoyés par le formulaire, les vérifies et construit utilisateur avec la UtilisateurFactory pour un usage en session</b>
 *
 */
public final class LoginForm {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "motdepasse";
	private static final String USERS_PATH = "/WEB-INF/database/utilisateurs.xml";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {
		/* Récupération des champs du formulaire */
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String motDePasse = getValeurChamp(request, CHAMP_PASS);

		Utilisateur utilisateur = new Utilisateur();

		/* Validation du champ email. */
		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		utilisateur.setEmail(email);

		/* Validation du champ mot de passe. */
		try {
			validationMotDePasse(motDePasse);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}
		utilisateur.setPassword(motDePasse);

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()) {
			try {
				utilisateur = verifierLogins(email, motDePasse, request);
				resultat = "Succès de la connexion.";
			} catch (Exception e) {
				setErreur("wrongCredentials", e.getMessage());
				resultat = "échec de la connexion.";
			}
		} else {
			resultat = "échec de la connexion.";
		}

		return utilisateur;
	}

	private Utilisateur verifierLogins(String email, String motDePasse, HttpServletRequest request) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(Utilisateurs.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Utilisateurs listUtilisateur = (Utilisateurs) jaxbUnmarshaller
				.unmarshal(new File(request.getServletContext().getRealPath(USERS_PATH)));
		for (Utilisateur u : listUtilisateur.getUtilisateur()) {
			if (u.getEmail().equals(email) && Pbkdf2.validatePassword(motDePasse,u.getPassword()))
				return u;
		}
		throw new Exception("Aucun utilisateur n'est enregistré avec cet email et ce mot de passe");
	}

	/**
	 * Valide l'adresse email saisie.
	 */
	private void validationEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationMotDePasse(String motDePasse) throws Exception {
		if (motDePasse != null) {
			if (motDePasse.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
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