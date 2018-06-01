package service;

import model.Candidature;
import model.Candidatures;
import model.Projet;
import model.Projets;

public interface WebService {
 
   /**
 * @return La liste des projets
 */
Projets getProjets();

   /**
 * @param Le nom du projet recherché
 * @return le projet recherché
 */
Projet getOneProjet(String nom);
   
	/**
	 * @param nomProjet Le nom du projet dont on veut les candidatures
	 * @return La liste des candidatures de ce projet
	 */
	Candidatures getCandidatures(String nomProjet);

	/**
	 * @param mail L'adresse mail de l'utilisateur dont on veut la candidature
	 * @param nomProjet Le nom du projet dont on veut la candidature
	 * @return La candidature que l'on veut
	 */
	Candidature getOneCandidature(String mail, String nomProjet);
	
	/**
	 * @param candidature La candidature que l'on souhaite ajouter
	 * @param projetName Le projet auquel on veut ajouter une candidature
	 */
	void addCandidature(Candidature candidature,String projetName);
}