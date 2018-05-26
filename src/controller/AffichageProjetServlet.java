package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.ProjetFactory;
import factory.ProjetFactoryImpl;
import model.Projet;

@WebServlet(urlPatterns = "/affichageProjet")
public class AffichageProjetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_LOGIN = "/login";
	public static final String ACCES_PROJET = "/WEB-INF/views/affichageProjet.jsp";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String CHEMIN = "localDirectoryPath";
	public static final String ATT_DB = "/projets.xml";
	public static final String ACCES_PROJETS = "/WEB-INF/views/projets.jsp";

	private ProjetFactory projetFactory;

	public void init() throws ServletException {
		/* Récupération d'une instance de candidatureFactory */
		this.projetFactory = new ProjetFactoryImpl();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

			String chemin = getServletContext().getInitParameter(CHEMIN) + ATT_DB;
			Projet p = new Projet();
			p = projetFactory.getOne(request.getParameter("projet"), chemin);
			request.setAttribute("projet", p);

			/* Affichage de la page restreinte */
			this.getServletContext().getRequestDispatcher(ACCES_PROJET).forward(request, response);
		}
	}
}
