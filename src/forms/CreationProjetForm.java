package forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import exception.FactoryException;
import exception.FormValidationException;
import factory.ProjetFactory;
import model.Candidatures;
import model.Projet;
import eu.medsea.mimeutil.MimeUtil;

public final class CreationProjetForm {
	private static final String CHAMP_NOM = "nomProjet";
	private static final String CHAMP_DESCRIPTION = "descriptifProjet";
	private static final String CHAMP_DEADLINE_CANDIDATURE = "deadLineCandidature";
	private static final String CHAMP_DEADLINE_PROJET = "deadLineProjet";
	private static final String CHAMP_NB_MAX_CANDIDATURE = "nbMaxCandidatures";
	private static final String CHAMP_IMAGE = "imageProjet";

	private static final int TAILLE_TAMPON = 10240; // 10ko

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private ProjetFactory projetFactory;

	public CreationProjetForm(ProjetFactory projetFactory) {
		this.projetFactory = projetFactory;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Projet creerProjet(HttpServletRequest request, String chemin, String filesPath) {
		String nomProjet = getValeurChamp(request, CHAMP_NOM);
		String description = getValeurChamp(request, CHAMP_DESCRIPTION);
		String deadLineCandidature = getValeurChamp(request, CHAMP_DEADLINE_CANDIDATURE);
		String deadLineProjet = getValeurChamp(request, CHAMP_DEADLINE_PROJET);
		String nbMaxCandidatures = getValeurChamp(request, CHAMP_NB_MAX_CANDIDATURE);
		String image = getValeurChamp(request, CHAMP_IMAGE);

		Projet projet = new Projet();

		traiterNomProjet(nomProjet, projet);
		traiterDescription(description, projet);
		traiterDeadLineCandidature(deadLineCandidature, projet);
		traiterDeadLineProjet(deadLineProjet, projet);
		traiterNbMaxCandidatures(nbMaxCandidatures, projet);
		traiterImage(projet, request, filesPath, nomProjet);
		// projet.setImage("./resources/img/default.jpg");
		projet.setCandidatures(new Candidatures());

		try {
			if (erreurs.isEmpty()) {
				projetFactory.create(projet, chemin);
				resultat = "Succès de la création du projet.";
			} else {
				resultat = "Échec de la création du projet.";
			}
		} catch (FactoryException e) {
			setErreur("imprévu", "Erreur imprévue lors de la création.");
			resultat = "Échec de la création du projet : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return projet;
	}

	private void traiterNomProjet(String nomProjet, Projet projet) {
		try {
			validationNomProjet(nomProjet);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		projet.setNom(nomProjet);
	}

	private void traiterDescription(String description, Projet projet) {
		try {
			validationDescription(description);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DESCRIPTION, e.getMessage());
		}
		projet.setDescriptif(description);
	}

	private void traiterDeadLineCandidature(String deadLineCandidature, Projet projet) {
		try {
			validationDeadLineCandidature(deadLineCandidature);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DEADLINE_CANDIDATURE, e.getMessage());
		}
		projet.setDeadLineCandidature(deadLineCandidature);
	}

	private void traiterDeadLineProjet(String deadLineProjet, Projet projet) {
		try {
			validationDeadLineProjet(deadLineProjet);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DEADLINE_PROJET, e.getMessage());
		}
		projet.setDeadLineCandidature(deadLineProjet);
	}

	private void traiterNbMaxCandidatures(String nbMaxCandidature, Projet projet) {
		try {
			validationNbMaxCandidature(nbMaxCandidature);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NB_MAX_CANDIDATURE, e.getMessage());
		}
		projet.setDeadLineCandidature(nbMaxCandidature);
	}

	private void traiterImage(Projet projet, HttpServletRequest request, String chemin, String projetName) {
		String image = null;
		try {
			image = validationImage(request, chemin, projetName);
		} catch (FormValidationException e) {
			setErreur(CHAMP_IMAGE, e.getMessage());
		}
		projet.setImage(image);
	}

	private void validationNomProjet(String nomProjet) throws FormValidationException {
		if (nomProjet != null) {
			if (nomProjet.length() < 2) {
				throw new FormValidationException("Le nom du projet doit au moins être défini par 2 caractères.");
			}
		} else {
			throw new FormValidationException("Merci de donner un nom au projet.");
		}
	}

	private void validationDescription(String description) throws FormValidationException {
		if (description != null) {
			if (description.length() < 10) {
				throw new FormValidationException("Veuillez au moins saisir une phrase pour décrire le projet");
			}
		} else {
			throw new FormValidationException("Veuillez décrire le projet avec plus de détail.");
		}
	}

	private void validationDeadLineCandidature(String deadLineCandidature) throws FormValidationException {
		if (!isValidDate(deadLineCandidature)) {
			throw new FormValidationException("Veuillez saisir une date limite de candidature valide");
		}
	}

	private void validationDeadLineProjet(String deadLineProjet) throws FormValidationException {
		if (!isValidDate(deadLineProjet)) {
			throw new FormValidationException("Veuillez saisir une date de cloture du projet valide");
		}
	}

	private int validationNbMaxCandidature(String nbMaxCandidature) throws FormValidationException {
		int temp;
		if (nbMaxCandidature != null) {
			try {
				temp = Integer.parseInt(nbMaxCandidature);
				if (temp < 1) {
					throw new FormValidationException("Il faut au moins un candidat pour ce projet.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new FormValidationException("La valeur maximum de candidat doit être un nombre.");
			}
		} else {
			temp = -1;
			throw new FormValidationException("Merci de définir le nombre de candidat maximum.");
		}
		return temp;
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

	private String validationImage(HttpServletRequest request, String chemin, String nomProjet)
			throws FormValidationException {
		/*
		 * Récupération du contenu du champ image du formulaire. Il faut ici utiliser la
		 * méthode getPart().
		 */
		String nomFichier = null;
		InputStream contenuFichier = null;
		try {
			Part part = request.getPart(CHAMP_IMAGE);
			nomFichier = getNomFichier(part);

			/*
			 * Si la méthode getNomFichier() a renvoyé quelque chose, il s'agit donc d'un
			 * champ de type fichier (input type="file").
			 */
			if (nomFichier != null && !nomFichier.isEmpty()) {
				/*
				 * Antibug pour Internet Explorer, qui transmet pour une raison mystique le
				 * chemin du fichier local à la machine du projet...
				 * 
				 * Ex : C:/dossier/sous-dossier/fichier.ext
				 * 
				 * On doit donc faire en sorte de ne sélectionner que le nom et l'extension du
				 * fichier, et de se débarrasser du superflu.
				 */
				nomFichier = nomProjet + "_" + nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
						.substring(nomFichier.lastIndexOf('\\') + 1);

				/* Récupération du contenu du fichier */
				contenuFichier = part.getInputStream();

				/* Extraction du type MIME du fichier depuis l'InputStream */
				MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
				Collection<?> mimeTypes = MimeUtil.getMimeTypes(contenuFichier);

				/*
				 * Si le fichier est bien une image, alors son en-tête MIME commence par la
				 * chaîne "image"
				 */
				if (mimeTypes.toString().startsWith("image")) {
					/* Écriture du fichier sur le disque */
					ecrireFichier(contenuFichier, nomFichier, chemin);
				} else {
					throw new FormValidationException("Le fichier envoyé doit être une image.");
				}
			} else {
				nomFichier = "./resources/img/default.jpg";
			}
		} catch (IllegalStateException e) {
			/*
			 * Exception retournée si la taille des données dépasse les limites définies
			 * dans la section <multipart-config> de la déclaration de notre servlet
			 * d'upload dans le fichier web.xml
			 */
			e.printStackTrace();
			throw new FormValidationException("Le fichier envoyé ne doit pas dépasser 1Mo.");
		} catch (IOException e) {
			/*
			 * Exception retournée si une erreur au niveau des répertoires de stockage
			 * survient (répertoire inexistant, droits d'accès insuffisants, etc.)
			 */
			e.printStackTrace();
			throw new FormValidationException("Erreur de configuration du serveur.");
		} catch (ServletException e) {
			/*
			 * Exception retournée si la requête n'est pas de type multipart/form-data.
			 */
			e.printStackTrace();
			throw new FormValidationException(
					"Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier.");
		}

		return nomFichier;
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

	/*
	 * Méthode utilitaire qui a pour unique but d'analyser l'en-tête
	 * "content-disposition", et de vérifier si le paramètre "filename" y est
	 * présent. Si oui, alors le champ traité est de type File et la méthode
	 * retourne son nom, sinon il s'agit d'un champ de formulaire classique et la
	 * méthode retourne null.
	 */
	private static String getNomFichier(Part part) {
		/* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			/* Recherche de l'éventuelle présence du paramètre "filename". */
			if (contentDisposition.trim().startsWith("filename")) {
				/*
				 * Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de
				 * fichier sans guillemets.
				 */
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		/* Et pour terminer, si rien n'a été trouvé... */
		return null;
	}

	/*
	 * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre sur
	 * le disque, dans le répertoire donné et avec le nom donné.
	 */
	private void ecrireFichier(InputStream contenuFichier, String nomFichier, String chemin)
			throws FormValidationException {
		/* Prépare les flux. */
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			/* Ouvre les flux. */
			entree = new BufferedInputStream(contenuFichier, TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

			/*
			 * Lit le fichier reçu et écrit son contenu dans un fichier sur le disque.
			 */
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur = 0;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} catch (Exception e) {
			throw new FormValidationException("Erreur lors de l'écriture du fichier sur le disque.");
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}
}