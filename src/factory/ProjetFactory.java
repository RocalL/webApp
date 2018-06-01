package factory;

import java.util.List;

import exception.FactoryException;
import model.Projet;
import model.Projets;

/**
 * Interface d'une projetFactory
 *
 */
public interface ProjetFactory {
	/**
	 * @param projet Le projet que l'on souhaite créer
	 * @throws FactoryException
	 */
	void create(Projet projet) throws FactoryException;

	/**
	 * @param name Le nom du projet que l'on souhaite récupérer
	 * @return le projet que l'on souhaite récupérer
	 * @throws FactoryException
	 */
	Projet getOne(String name) throws FactoryException;

	/**
	 * @return la liste des projets sous forme d'objet Projets
	 * @throws FactoryException
	 */
	Projets getProjets() throws FactoryException;
	
	/**
	 * @return la liste des projets sous forme de List
	 * @throws FactoryException
	 */
	List<Projet> getProjetsAsList() throws FactoryException;

	/**
	 * @param projet le projet à supprimer
	 * @throws FactoryException
	 */
	void delete(Projet projet) throws FactoryException;
	
	/**
	 * @param projet le projet à mettre à jour
	 * @throws FactoryException
	 */
	void update(Projet projet) throws FactoryException;
	
	/**
	 * @param projetName le nom du projet dont-il faut vérifier si il est plein
	 * @return oui si il reste de la place, non si plein
	 * @throws FactoryException
	 */
	boolean stillCandidaturePlace(String projetName) throws FactoryException;
}