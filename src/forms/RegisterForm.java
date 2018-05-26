package forms;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import factory.UtilisateurFactory;
import model.Utilisateur;
import util.Pbkdf2;

public class RegisterForm {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "motdepasse";
	private static final String CHAMP_CONF = "confirmation";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_TEL = "tel";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}
	private UtilisateurFactory utilisateurFactory;

	public RegisterForm(UtilisateurFactory utilisateurFactory) {
		this.utilisateurFactory = utilisateurFactory;
	}
	
	public Utilisateur inscrireUtilisateur(HttpServletRequest request, String chemin) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String email = getValeurChamp(request, CHAMP_EMAIL);//toto
		String motDePasse = getValeurChamp(request, CHAMP_PASS);
		String confirmation = getValeurChamp(request, CHAMP_CONF);
		String nom = getValeurChamp(request, CHAMP_NOM);
		String prenom = getValeurChamp(request, CHAMP_PRENOM);
		String tel = getValeurChamp(request, CHAMP_TEL);

		Utilisateur utilisateur = new Utilisateur();

		try {
			validationTel(tel);
		} catch(Exception e) {
			setErreur(CHAMP_TEL, e.getMessage());
		}
		utilisateur.setTel(tel);

		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		utilisateur.setEmail(email);

		try {
			validationMotsDePasse(motDePasse, confirmation);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
			setErreur(CHAMP_CONF, null);
		}
		utilisateur.setPassword(Pbkdf2.generateStorngPasswordHash(motDePasse));

		try {
			validationNom(nom);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		utilisateur.setNom(nom);

		if (erreurs.isEmpty()) {
			resultat = "Succès de l'inscription.";
		} else {
			resultat = "échec de l'inscription.";
		}

		try {
			validationPrenom(prenom);
		} catch (Exception e) {
			setErreur(CHAMP_PRENOM, e.getMessage());
			}
		utilisateur.setPrenom(prenom);

		try {
			validationTel(tel);
		} catch (Exception e) {
			setErreur(CHAMP_TEL, e.getMessage());
		}

		utilisateur.setTel(tel);
		System.out.println(request.getServletContext().getRealPath(chemin));
		try {
			if(erreurs.isEmpty()){
				utilisateurFactory.create(utilisateur,chemin);
				resultat = "Succès de la création du nouvel utilisateur";
			}
		} catch (Exception e) {
			setErreur("ParserError", e.getMessage());
			e.printStackTrace();
		}
			

		return utilisateur;
	}
	
	private void validationTel(String tel) throws Exception {
		if(tel != null) {
			if (!tel.matches("^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})$")) {
				throw new Exception("Veuillez saisir un numéro de téléphone valide");
			}
		} else {
			throw new Exception("Veuillez saisir un numéro de téléphone.");
		}

	}

	private void validationEmail(String email) throws Exception {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
		if (motDePasse != null && confirmation != null) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (motDePasse.length() < 3) {
				throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	private void validationNom(String nom) throws Exception {
		if (nom == null) {
			throw new Exception("Veuillez saisir un nom");
		}
		else if(nom.length() < 3) {
			throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}
	private void validationPrenom(String prenom) throws Exception {
		if (prenom == null) {
			throw new Exception("Veuillez saisir un prénom");
		}
		else if(prenom.length() < 3) {
			throw new Exception("Le prénom d'utilisateur doit contenir au moins 3 caractères.");
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
			return valeur.trim();
		}
	}
}
