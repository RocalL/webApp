package factory;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import controller.InitializationServlet;
import exception.FactoryException;
import model.Projet;
import model.Projets;
import util.JaxParser;

public class ProjetFactoryImpl implements ProjetFactory {

	public ProjetFactoryImpl() {
	}

	public void create(Projet projet) throws FactoryException {
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			// Read
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);
			for (Projet p : listProjets.getProjet()) {

				if (p.getNom().equals(projet.getNom())) {
					throw new FactoryException("Un projet portant ce nom existe déjà");
				}
			}
			listProjets.getProjet().add(projet);
			// Write
			JaxParser.marshal(listProjets, file, InitializationServlet.ATT_PROJETS_XML);
			System.out.println(projet);
			System.out.println("ajoutée à la base de donnée");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
	}

	public Projet getOne(String nom) throws FactoryException {
		Projet p = new Projet();
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			// Read
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);
			if (listProjets.getProjetByNom(nom) != null) {
				p = listProjets.getProjetByNom(nom);
			}
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
		return p;
	}

	public List<Projet> getAll() throws FactoryException {
		Projets listProjets = null;
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			listProjets = JaxParser.unmarshal(Projets.class, file);
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
		return listProjets.getProjet();
	}

	public void delete(Projet projet) throws FactoryException {
	}

	@Override
	public void update(Projet projet) throws FactoryException {
	}
}
