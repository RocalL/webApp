package factory;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import controller.InitializationServlet;
import exception.FactoryException;
import model.Candidature;
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
			else {
				throw new FactoryException("Aucun projet n'existe avec ce nom");
			}
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
		return p;
	}

	public List<Projet> getProjetsAsList() throws FactoryException {
		return getProjets().getProjet();
	}
	
	public Projets getProjets() {
		Projets listProjets = null;
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			listProjets = JaxParser.unmarshal(Projets.class, file);
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
		return listProjets;
	}

	@Override
	public void delete(Projet projet) throws FactoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Projet projet) throws FactoryException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean stillCandidaturePlace(String projetName) throws FactoryException {
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);
			int count = 0;
			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(projetName)) {
					for (Candidature c : p.getCandidatures().getCandidature()) {
						if (c.getEtatCandidature().equals("Valide")) {
							count++;
						}
					}
					if (count >= p.getNbMaxCandidatures()) {
						return false;
					}
					else {
						return true;
					}
				}
			}
			throw new FactoryException("Aucun projet n'existe avec ce nom");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
		return false;
	}
}
