package factory;

import java.util.List;

import exception.FactoryException;
import model.Utilisateur;

/**
 * Interface d'une utilisateurFactory
 *
 */
public interface UtilisateurFactory {
	/**
	 * @param utilisateur L'utilisateur à créer
	 * @throws FactoryException
	 */
	/**
	 * @param utilisateur L'utilisateur à créer
	 * @throws FactoryException
	 */
	void create(Utilisateur utilisateur) throws FactoryException;

	/**
	 * @param name L'adresse mail de l'utilisateur à récupérer
	 * @return l'utilisateur récupéré
	 * @throws FactoryException
	 */
	Utilisateur getOne(String email) throws FactoryException;

	/**
	 * @return la liste des utilisateurs
	 * @throws FactoryException
	 */
	List<Utilisateur> getAll() throws FactoryException;

	/**
	 * @param utilisateur L'utilisateur à détruire
	 * @throws FactoryException
	 */
	void delete(Utilisateur utilisateur) throws FactoryException;
	
	/**
	 * @param utilisateur L'utilisateur à mettre à jour
	 * @throws FactoryException
	 */
	void update(Utilisateur utilisateur) throws FactoryException;
}