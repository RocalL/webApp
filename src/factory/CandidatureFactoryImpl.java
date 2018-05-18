package factory;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import exception.FactoryException;
import model.Candidature;
import model.Projet;
import model.Projets;
import util.JaxParser;

public class CandidatureFactoryImpl implements CandidatureFactory {

	@Override
	public void create(Candidature candidature, Projet projet, String chemin) throws FactoryException {
		try {
			File file = new File(chemin);
			// Read
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);
			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(projet.getNom())) {
					p.addCandidature(candidature);
				}
				// implémenter une exception pour projet delete en cours de workflow
			}
			// Write
			 JaxParser.marshal(listProjets, file);
			 System.out.println(candidature);
			 System.out.println("ajoutée au projet");
			 System.out.println(projet);
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
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
