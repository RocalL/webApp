package factory;

import java.util.List;

import exception.FactoryException;
import model.Candidature;
import model.Candidatures;
import model.Projet;

/**
 * Interface d'une candidatureFactory
 *
 */
public interface CandidatureFactory {
	/**
	 * @param candidature La candidature à créer
	 * @param projet Le projet sur lequel créer une candidature
	 * @throws FactoryException
	 */
	void create(Candidature candidature, Projet projet) throws FactoryException;
	
	/**
	 * @param candidature La candidature à créer
	 * @param projet Le projet sur lequel créer une candidature
	 * @throws FactoryException
	 */
	void create(Candidature candidature, String projetName) throws FactoryException;

	/**
	 * @param userMail Le mail de l'utilisateur qui a posté la candidature
	 * @param nomProjet Le nom du projet concerné par la candidature
	 * @return la candidature
	 * @throws FactoryException
	 */
	Candidature getOne(String userMail, String nomProjet) throws FactoryException;

	/**
	 * @param nomProjet Le nom du projet concerné par les candidatures
	 * @return la liste sous forme d'objet Candidatures des candidatures de ce projet
	 * @throws FactoryException
	 */
	Candidatures getCandidatures(String nomProjet) throws FactoryException;
	
	/**
	 * @param nomProjet Le nom du projet concerné par les candidatures
	 * @return la liste sous forme de List des candidatures de ce projet
	 * @throws FactoryException
	 */
	List<Candidature> getCandidaturesAsList(String nomProjet) throws FactoryException;

	/**
	 * @param candidature la candidature à supprimer
	 * @param projet le projet concerné par la candidature
	 * @throws FactoryException
	 */
	void delete(Candidature candidature,Projet projet) throws FactoryException;
	
	/**
	 * @param candidature la candidature à mettre à jour
	 * @param projet le projet concerné par la candidature
	 * @throws FactoryException
	 */
	void update(Candidature candidature,Projet projet) throws FactoryException;
}