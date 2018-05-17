package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Projets;
import util.JaxParser;

@WebServlet(urlPatterns = "/projets")
public class ProjetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_LOGIN = "/login";
	public static final String ACCES_PROJET = "/WEB-INF/views/projets.jsp";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String CHEMIN = "/projets.xml";

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
			try {
				Projets listProjets = JaxParser.unmarshal(Projets.class,
						new File(getServletContext().getInitParameter("localDirectoryPath") + CHEMIN));
				request.setAttribute("projets", listProjets);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher(ACCES_PROJET).forward(request, response);
		}
	}
}