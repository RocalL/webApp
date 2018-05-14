package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/affichageCandidatures")
public class AffichageCandidaturesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ACCES_LOGIN = "/login";
	public static final String ACCES_CANDIDATS = "/WEB-INF/views/affichageCandidatures.jsp";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public AffichageCandidaturesServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* R�cup�ration de la session depuis la requ�te */
		HttpSession session = request.getSession();
		/*
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connect�.
		 */
		if (session.getAttribute(ATT_SESSION_USER) == null) {
			/* Redirection vers la page publique */
			response.sendRedirect(request.getContextPath() + ACCES_LOGIN);
		} else {
			/* Affichage de la page restreinte */
			this.getServletContext().getRequestDispatcher(ACCES_CANDIDATS).forward(request, response);
		}
	}
}
