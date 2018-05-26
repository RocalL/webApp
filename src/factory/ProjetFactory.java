package factory;

import java.util.List;

import exception.FactoryException;
import model.Projet;

public interface ProjetFactory {
	void create(Projet projet) throws FactoryException;

	Projet getOne(String name) throws FactoryException;

	List<Projet> getAll() throws FactoryException;

	void delete(Projet projet) throws FactoryException;
	
	void update(Projet projet) throws FactoryException;
}