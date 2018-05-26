package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Projet;
import model.Candidature;
import factory.CandidatureFactory;
import factory.CandidatureFactoryImpl;
import factory.ProjetFactory;
import factory.ProjetFactoryImpl;

@WebServlet(urlPatterns = "/affichageCandidatures")
public class AffichageCandidaturesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_LOGIN = "/login";
	public static final String ACCES_CANDIDATS = "/WEB-INF/views/affichageCandidatures.jsp";
	public static final String ACCES_PROJETS = "/WEB-INF/views/projets.jsp";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	private CandidatureFactory candidatureFactory;
	private ProjetFactory projetFactory;

	public void init() throws ServletException {
		/* Récupération d'une instance de candidatureFactory */
		this.candidatureFactory = new CandidatureFactoryImpl();
		this.projetFactory = new ProjetFactoryImpl();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();
		/*
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connect�.
		 */
		if (session.getAttribute(ATT_SESSION_USER) == null) {
			/* Redirection vers la page publique */
			response.sendRedirect(request.getContextPath() + ACCES_LOGIN);
		} else {

			List<Candidature> c = new ArrayList<Candidature>();
			c = candidatureFactory.getAll(request.getParameter("projet"));
			request.setAttribute("candidature", c);

			Projet p = new Projet();
			p = projetFactory.getOne(request.getParameter("projet"));
			request.setAttribute("projet", p);

			this.getServletContext().getRequestDispatcher(ACCES_CANDIDATS).forward(request, response);
		}
	}
}
