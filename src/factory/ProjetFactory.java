package factory;

import java.util.List;

import exception.FactoryException;
import model.Projet;
import model.Projets;

public interface ProjetFactory {
	void create(Projet projet) throws FactoryException;

	Projet getOne(String name) throws FactoryException;

	Projets getProjets() throws FactoryException;
	
	List<Projet> getProjetsAsList() throws FactoryException;

	void delete(Projet projet) throws FactoryException;
	
	void update(Projet projet) throws FactoryException;
	
	boolean stillCandidaturePlace(String projetName) throws FactoryException;
}