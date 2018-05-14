package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import forms.CreationCandidatureForm;
import model.Candidature;

/**
 * Servlet implementation class CreationCandidature
 */
@WebServlet(urlPatterns = "/creationCandidature")
public class CreationCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_SUCCES = "/WEB-INF/projets.jsp";
	public static final String VUE_FORM = "/WEB-INF/views/creationCandidature.jsp";
	public static final String ACCES_LOGIN = "/login";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_CANDIDATURE = "candidature";
	public static final String ATT_FORM = "form";
	public static final String CHEMIN = "/WEB-INF/database/projets.xml";

	public CreationCandidatureServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();
		/*
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connecté.
		 */
		if (session.getAttribute(ATT_SESSION_USER) == null) {
			/* Redirection vers la page publique */
			response.sendRedirect(request.getContextPath() + ACCES_LOGIN);
		} else {
			/* Affichage de la page restreinte */
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			/* Préparation de l'objet formulaire */
			CreationCandidatureForm form = new CreationCandidatureForm();

			/* Traitement de la requête et récupération du bean en résultant */
			Candidature candidature = form.creerCandidature(request, CHEMIN);
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_CANDIDATURE, candidature);

			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
