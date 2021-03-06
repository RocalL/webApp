package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.ProjetFactory;
import factory.ProjetFactoryImpl;
import forms.CreationProjetForm;
import model.Projet;

/**
 * <b>Ce servlet gère le mécanisme de création d'un projet</b>
 *
 */
@WebServlet( urlPatterns = { "/creationProjet" } )
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class CreationProjetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_FORM = "/WEB-INF/views/creationProjet.jsp";
	public static final String ACCES_LOGIN = "/login";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_PROJET = "projet";
	public static final String ATT_FILESPATH = "/files/";

	public static final String SERVLET_SUCCES = "/projets";

	private ProjetFactory projetFactory;

	public void init() throws ServletException {
		/* Récupération d'une instance de projetFactory */
		this.projetFactory = new ProjetFactoryImpl();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();
		if (session.getAttribute(ATT_SESSION_USER) == null) {
			/* Redirection vers la page publique */
			response.sendRedirect(request.getContextPath() + ACCES_LOGIN);
		} else {
			/* Affichage de la page restreinte */
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();
		if (session.getAttribute(ATT_SESSION_USER) == null) {
			/* Redirection vers la page publique */
			response.sendRedirect(request.getContextPath() + ACCES_LOGIN);
		} else {
			String filesPath = InitializationServlet.CHEMIN + ATT_FILESPATH;

			/* Préparation de l'objet formulaire */
			CreationProjetForm form = new CreationProjetForm(projetFactory);

			/* Traitement de la requête et récupération du bean en résultant */
			Projet projet = form.creerProjet(request, filesPath);
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_PROJET, projet);


			/* Si aucune erreur */
			if (form.getErreurs().isEmpty()) {
				/* Affichage de la fiche récapitulative */
				response.sendRedirect(request.getContextPath() + SERVLET_SUCCES);
			} else {
				/* Sinon, ré-affichage du formulaire de création avec les erreurs */
				this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
			}
		}
	}
}