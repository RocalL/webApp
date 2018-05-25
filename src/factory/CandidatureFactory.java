package factory;

import java.util.List;

import exception.FactoryException;
import model.Candidature;
import model.Projet;

public interface CandidatureFactory {
	void create(Candidature candidature, Projet projet, String chemin) throws FactoryException;

	Candidature getOne(String userMail, String nomProjet, String chemin) throws FactoryException;

	List<Candidature> getAll(String nomProjet, String chemin) throws FactoryException;

	void delete(Candidature candidature,Projet projet, String chemin) throws FactoryException;
	
	void update(Candidature candidature,Projet projet, String chemin) throws FactoryException;
}