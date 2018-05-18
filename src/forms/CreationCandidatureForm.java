package forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import eu.medsea.mimeutil.MimeUtil;
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
	private static final String CHAMP_FICHIERS = "fichiers";
	private static final String CHAMP_DELAI = "delai";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	private static final int TAILLE_TAMPON = 10240; // 10ko

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
	public Candidature creerCandidature(HttpServletRequest request, String chemin) {
		HttpSession session = request.getSession();
		String raisonSociale = getValeurChamp(request, CHAMP_RAISONSOCIALE);
		String siret = getValeurChamp(request, CHAMP_SIRET);
		String ca = getValeurChamp(request, CHAMP_CA);
		String delai = getValeurChamp(request, CHAMP_DELAI);
		String website = getValeurChamp(request, CHAMP_WEBSITE);
		String devis = getValeurChamp(request, CHAMP_DEVIS);
		String fichiers = getValeurChamp(request, CHAMP_FICHIERS);

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
		//traiterWebsite(website, candidature);
		//traiterDevis(devis,candidature,chemin);
		//traiterFichiers(fichiers,candidature,chemin);

		candidature.setUtilisateur(utilisateur);
		candidature.setStructure(structure);
		candidature.setRepProjet(repProjet);
		candidature.setDateCandidature(dateFormat.format(date));
		candidature.setEtatCandidature("En attente");

		try {
			if (erreurs.isEmpty()) {
				Projet projet = projetFactory.getOne(request.getParameter("projet"),chemin);
				candidatureFactory.create(candidature,projet,chemin);
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

    private void traiterDevis(RepProjet repProjet, HttpServletRequest request, String chemin ) {
        String devis = null;
        try {
            devis = validationDevis( request, chemin );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_DEVIS, e.getMessage() );
        }
        repProjet.setDevis(devis);
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

	private long validationSiret(String siret) throws FormValidationException{
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

	private int validationCa(String ca) throws FormValidationException{
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

	private String validationDevis(HttpServletRequest request, String chemin) throws FormValidationException {
		/*
		 * Récupération du contenu du champ image du formulaire. Il faut ici utiliser la
		 * méthode getPart().
		 */
		String nomFichier = null;
		InputStream contenuFichier = null;
		try {
			Part part = request.getPart(CHAMP_DEVIS);
			nomFichier = getNomFichier(part);

			/*
			 * Si la méthode getNomFichier() a renvoyé quelque chose, il s'agit donc d'un
			 * champ de type fichier (input type="file").
			 */
			if (nomFichier != null && !nomFichier.isEmpty()) {
				/*
				 * Antibug pour Internet Explorer, qui transmet pour une raison mystique le
				 * chemin du fichier local à la machine du candidature...
				 * 
				 * Ex : C:/dossier/sous-dossier/fichier.ext
				 * 
				 * On doit donc faire en sorte de ne sélectionner que le nom et l'extension du
				 * fichier, et de se débarrasser du superflu.
				 */
				nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
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