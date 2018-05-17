package factory;

import java.util.List;

import exception.FactoryException;
import model.Candidature;

public interface CandidatureFactory {
	void create(Candidature candidature, String chemin) throws FactoryException;

	Candidature getOne(String name) throws FactoryException;

	List<Candidature> getAll() throws FactoryException;

	void delete(Candidature candidature) throws FactoryException;
	
	void update(Candidature candidature) throws FactoryException;
}