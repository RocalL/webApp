package factory;

import java.util.List;

import exception.FactoryException;
import model.Utilisateur;

public interface UtilisateurFactory {
	void create(Utilisateur utilisateur, String chemin) throws FactoryException;

	Utilisateur getOne(String name) throws FactoryException;

	List<Utilisateur> getAll() throws FactoryException;

	void delete(Utilisateur utilisateur) throws FactoryException;
	
	void update(Utilisateur utilisateur) throws FactoryException;
}