package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forms.RegisterForm;
import model.Utilisateur;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/WEB-INF/views/register.jsp";
    public static final String CHEMIN = "/WEB-INF/database/utilisateurs.xml";

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Pr�paration de l'objet formulaire */
        RegisterForm form = new RegisterForm();
		
        /* Appel au traitement et � la validation de la requ�te, et r�cup�ration du bean en r�sultant */
        Utilisateur utilisateur = form.inscrireUtilisateur(request, CHEMIN);
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
		
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}