package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forms.LoginForm;
import model.Utilisateur;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/WEB-INF/views/login.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Pr�paration de l'objet formulaire */
		LoginForm form = new LoginForm();

		/* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
		Utilisateur utilisateur = form.connecterUtilisateur(request);

		/* R�cup�ration de la session depuis la requ�te */
		HttpSession session = request.getSession();
		/* Stockage du formulaire et du bean dans l'objet request */
		
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);
		
		/**
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean Utilisateur �
		 * la session, sinon suppression du bean de la session.
		 */
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, utilisateur);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}
}