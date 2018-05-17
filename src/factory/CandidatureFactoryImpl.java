package factory;

import java.util.List;

import exception.FactoryException;
import model.Candidature;

public class CandidatureFactoryImpl implements CandidatureFactory {

	@Override
	public void create(Candidature candidature, String chemin) throws FactoryException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Candidature getOne(String name) throws FactoryException {
		// FAUT METTRE NOTRE CODE ICI
		return null;
	}

	@Override
	public List<Candidature> getAll() throws FactoryException {
		// FAUT METTRE NOTRE CODE ICI
		return null;
	}

	@Override
	public void delete(Candidature candidature) throws FactoryException {
		// FAUT METTRE NOTRE CODE ICI
		
	}

	@Override
	public void update(Candidature candidature) throws FactoryException {
		// FAUT METTRE NOTRE CODE ICI
		
	}

}
