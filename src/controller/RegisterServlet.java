package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.ProjetFactoryImpl;
import factory.UtilisateurFactory;
import factory.UtilisateurFactoryImpl;
import forms.RegisterForm;
import model.Projet;
import model.Utilisateur;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_FORM = "/WEB-INF/views/register.jsp";
	public static final String CHEMIN = "localDirectoryPath";
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/WEB-INF/views/register.jsp";
	public static final String ATT_DB = "/utilisateurs.xml";
	
	public static final String SERVLET_SUCCES = "/register";
	
    private UtilisateurFactory utilisateurFactory;
    

	public void init() throws ServletException {
		/* Récupération d'une instance de projetFactory */
		this.utilisateurFactory = new UtilisateurFactoryImpl();
	}


	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	
		String chemin = getServletContext().getInitParameter(CHEMIN) + ATT_DB;
    	
    	System.out.println("/* Pr�paration de l'objet formulaire */");
        RegisterForm form = new RegisterForm(utilisateurFactory);
        
        System.out.println("/* Appel au traitement et � la validation de la requ�te, et r�cup�ration du bean en r�sultant */");
        Utilisateur utilisateur;
		try {
			utilisateur = form.inscrireUtilisateur(request, chemin);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );
		
		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {
			/* Affichage de la fiche récapitulative */
			response.sendRedirect(request.getContextPath() + SERVLET_SUCCES);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
    }
}