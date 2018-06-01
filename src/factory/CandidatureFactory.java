package factory;

import java.util.List;

import exception.FactoryException;
import model.Candidature;
import model.Candidatures;
import model.Projet;

public interface CandidatureFactory {
	void create(Candidature candidature, Projet projet) throws FactoryException;
	
	void create(Candidature candidature, String projetName) throws FactoryException;

	Candidature getOne(String userMail, String nomProjet) throws FactoryException;

	Candidatures getCandidatures(String nomProjet) throws FactoryException;
	
	List<Candidature> getCandidaturesAsList(String nomProjet) throws FactoryException;

	void delete(Candidature candidature,Projet projet) throws FactoryException;
	
	void update(Candidature candidature,Projet projet) throws FactoryException;
}