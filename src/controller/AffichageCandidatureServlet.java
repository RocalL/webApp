package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Candidature;
import model.Candidatures;
import util.JaxParser;

@WebServlet(urlPatterns = "/affichageCandidature")
public class AffichageCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_LOGIN = "/login";
	public static final String ACCES_CANDIDAT = "/WEB-INF/views/affichageCandidat.jsp";
	public static final String ACCES_CANDIDATS = "/WEB-INF/views/affichageCandidatures.jsp";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String CHEMIN = "/WEB-INF/database/projets.xml";

	public AffichageCandidatureServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération de la session depuis la requ�te */
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
				Candidatures listCandidats = JaxParser.unmarshal(Candidatures.class,
						new File(request.getServletContext().getRealPath(CHEMIN)));
				Candidature c = new Candidature();
				if (listCandidats.getCandidatureByMail(request.getParameter("candidature")) != null) {
					c = listCandidats.getCandidatureByMail(request.getParameter("candidature"));
				}
				request.setAttribute("candidature", c);
				System.out.println(c); /* vide !! :( */

				/* Affichage de la page restreinte */
				this.getServletContext().getRequestDispatcher(ACCES_CANDIDAT).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				this.getServletContext().getRequestDispatcher(ACCES_CANDIDATS).forward(request, response);
			}
		}
	}
}
