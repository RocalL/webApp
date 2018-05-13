package controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import forms.CreationCandidatureForm;
import model.Candidature;
import model.Projet;
import services.Projets;

/**
 * Servlet implementation class CreationCandidature
 */
@WebServlet(urlPatterns = "/creationCandidature")
public class CreationCandidatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_SUCCES = "/WEB-INF/projets.jsp";
	public static final String VUE_FORM = "/WEB-INF/views/creationCandidature.jsp";
	public static final String ACCES_LOGIN = "/login";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_CANDIDATURE = "candidature";
	public static final String ATT_FORM = "form";
	public static final String CHEMIN = "/WEB-INF/database/projets.xml";

	public CreationCandidatureServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			/* Pr�paration de l'objet formulaire */
			CreationCandidatureForm form = new CreationCandidatureForm();

			/* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
			Candidature candidature = form.creerCandidature(request, CHEMIN);
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_CANDIDATURE, candidature);

			// Read
			JAXBContext jaxbContext = JAXBContext.newInstance(Projets.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			System.out.println(unmarshaller.unmarshal(new File(request.getServletContext().getRealPath(CHEMIN))));
			//System.out.println(request.getServletContext().getRealPath(CHEMIN));
			//System.out.println(projets.getProjets());
			//System.out.println(projets.getProjetById(Integer.parseInt(request.getParameter("projet"))));

			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}