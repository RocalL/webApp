package factory;

import java.util.List;

import exception.FactoryException;
import model.Utilisateur;

public class UtilisateurFactoryImpl implements UtilisateurFactory {
	@Override
	public void create(Utilisateur utilisateur, String chemin) throws FactoryException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Utilisateur getOne(String name) throws FactoryException {
		// FAUT METTRE NOTRE CODE ICI
		return null;
	}

	@Override
	public List<Utilisateur> getAll() throws FactoryException {
		// FAUT METTRE NOTRE CODE ICI
		return null;
	}

	@Override
	public void delete(Utilisateur utilisateur) throws FactoryException {
		// FAUT METTRE NOTRE CODE ICI
		
	}

	@Override
	public void update(Utilisateur utilisateur) throws FactoryException {
		// FAUT METTRE NOTRE CODE ICI
		
	}
}
