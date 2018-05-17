package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Candidature;
import model.Candidatures;

/**
 * Servlet implementation class ValidateCandidature
 */
@WebServlet(urlPatterns = "/validateCandidature")
public class ValidateCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_SUCCES = "/WEB-INF/projets.jsp";
	public static final String ACCES_LOGIN = "/login";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String CHEMIN = "/WEB-INF/database/projets.xml";

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
			try {
				Candidatures listCandidatures = new Candidatures();
				Candidature c = new Candidature();
				if(listCandidatures.getCandidatureByMail(request.getParameter("candidature")) != null) {
					c = listCandidatures.getCandidatureByMail(request.getParameter("candidature"));
				}
				
				c.setEtatCandidature("validée");
				System.out.println("coucou");
				
				request.setAttribute("candidature", c);
				
				this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
			}
			
		}
	}

}
