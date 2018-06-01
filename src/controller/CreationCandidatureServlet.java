package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.CandidatureFactory;
import factory.CandidatureFactoryImpl;
import factory.ProjetFactory;
import factory.ProjetFactoryImpl;
import forms.CreationCandidatureForm;
import model.Candidature;

/**
 * <b>Ce servlet gère le mécanisme de création d'une candidature </b>
 *
 */
@WebServlet(urlPatterns = "/creationCandidature")
public class CreationCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_FORM = "/WEB-INF/views/creationCandidature.jsp";
	public static final String ACCES_LOGIN = "/login";
	public static final String ATT_FORM = "form";
	public static final String ATT_CANDIDATURE = "candidature";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	public static final String SERVLET_SUCCES = "/projets";

	private CandidatureFactory candidatureFactory;
	private ProjetFactory projetFactory;

	public void init() throws ServletException {
		/* Récupération d'une instance de projetFactory */
		this.candidatureFactory = new CandidatureFactoryImpl();
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
			/* Préparation de l'objet formulaire */
			CreationCandidatureForm form = new CreationCandidatureForm(candidatureFactory, projetFactory);

			/* Traitement de la requête et récupération du bean en résultant */
			Candidature candidature = form.creerCandidature(request);
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_CANDIDATURE, candidature);

			/* ré-affichage du formulaire de création */
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}
}