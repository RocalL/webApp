package controller;

import javax.servlet.*;
import javax.servlet.http.*;

public class InitializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String LOCALDIRECTORY = "localDirectoryPath";
	public static String CHEMIN;
	public static String ATT_PROJETS_XML;
	public static String ATT_UTILISATEURS_XML;
	public static String ATT_PROJETS_XSD;
	public static String ATT_UTILISATEURS_XSD;

	public void init() throws ServletException {
		CHEMIN = getServletContext().getInitParameter(LOCALDIRECTORY);
		ATT_PROJETS_XML = CHEMIN + "/projets.xml";
		ATT_UTILISATEURS_XML = CHEMIN + "/utilisateurs.xml";
		ATT_PROJETS_XSD = CHEMIN + "/projets.xsd";
		ATT_UTILISATEURS_XSD = CHEMIN + "/utilisateurs.xsd";
	}
}