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
import model.Projet;

/**
 * <b>Ce servlet gère le changement de l'état d'une candidature en "Valide"</b>
 */
@WebServlet(urlPatterns = "/validateCandidature")
public class ValidateCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_SUCCES = "/WEB-INF/views/affichageCandidatures.jsp";
	public static final String ACCES_LOGIN = "/login";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_CANDIDATURE = "candidature";

	private CandidatureFactory candidatureFactory;
	private ProjetFactory projetFactory;

	public void init() throws ServletException {
		/* Récupération d'une instance de projetFactory */
		this.candidatureFactory = new CandidatureFactoryImpl();
		this.projetFactory = new ProjetFactoryImpl();
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
			/* Modification de l'état de la candidature courante */
			/*
			 * Lecture du paramètre 'chemin' passé à la servlet via la déclaration dans le
			 * web.xml
			 */

			candidatureFactory.update(
					candidatureFactory.getOne(request.getParameter("utilisateur"), request.getParameter("projet")),
					projetFactory.getOne(request.getParameter("projet")));

			Projet p = new Projet();
			p = projetFactory.getOne(request.getParameter("projet"));

			request.setAttribute("projet", p);
			/* ré-affichage des candidatures */
			this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);

		}
	}

}
