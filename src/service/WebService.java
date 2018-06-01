package service;

import model.Candidature;
import model.Candidatures;
import model.Projet;
import model.Projets;

public interface WebService {
 
   Projets getProjets();

   Projet getOneProjet(String nom);
   
	Candidatures getCandidatures(String nomProjet);

	Candidature getOneCandidature(String mail, String nomProjet);
	
	void addCandidature(Candidature candidature,String projetName);
}