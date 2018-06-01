package factory;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import controller.InitializationServlet;
import exception.FactoryException;
import model.Candidature;
import model.Candidatures;
import model.Projet;
import model.Projets;
import util.JaxParser;

public class CandidatureFactoryImpl implements CandidatureFactory {

	@Override
	public void create(Candidature candidature, Projet projet) throws FactoryException {
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			// Read
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);
			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(projet.getNom())) {
					p.addCandidature(candidature);
					// Write
					JaxParser.marshal(listProjets, file, InitializationServlet.ATT_PROJETS_XML);
					System.out.println(candidature);
					System.out.println("ajoutée au projet");
					System.out.println(projet);
					return;
				}
			}
			throw new FactoryException("Ce projet n'existe pas ou plus");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void create(Candidature candidature, String projetName) throws FactoryException {
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			// Read
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);
			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(projetName)) {
					p.addCandidature(candidature);
					// Write
					JaxParser.marshal(listProjets, file, InitializationServlet.ATT_PROJETS_XML);
					System.out.println(candidature);
					System.out.println("ajoutée au projet");
					System.out.println(projetName);
					return;
				}
			}
			throw new FactoryException("Ce projet n'existe pas ou plus");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Candidature getOne(String userMail, String nomProjet) throws FactoryException {
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			// Read
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);

			// Parcours des projets
			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(nomProjet)) {
					// Parcours des candidats
					Candidatures c = p.getCandidatures();
					// System.out.println(c.toString());

					return c.getCandidatureByMail(userMail);
				}
			}
			throw new FactoryException("Aucun projet n'existe avec ce nom");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Candidatures getCandidatures(String nomProjet) throws FactoryException {
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			// Read
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);

			// Parcours des projets
			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(nomProjet)) {
					// Parcours des candidatures
					return p.getCandidatures();
				}
			}
			throw new FactoryException("Aucun projet n'existe avec ce nom");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Candidature> getCandidaturesAsList(String nomProjet) throws FactoryException {
		return getCandidatures(nomProjet).getCandidature();
	}

	@Override
	public void delete(Candidature candidature, Projet projet) throws FactoryException {
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);
			Candidatures listCandidatures = new Candidatures();// JaxParser.unmarshal(Candidatures.class, file);

			Candidature candid = new Candidature();

			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(projet.getNom())) {
					for (Candidature c : p.getCandidatures().getCandidature()) {
						if (c.getUtilisateur().getEmail().equals(candidature.getUtilisateur().getEmail())) {
							System.out.println(c.toString());
							listCandidatures = p.getCandidatures();
							candid = c;
							System.out.println(candid.toString());
							System.out.println(listCandidatures.getCandidature().size());
							listCandidatures.deleteCandidature(candid);
							System.out.println(listCandidatures.getCandidature().size());

							// Write
							JaxParser.marshal(listProjets, file, InitializationServlet.ATT_PROJETS_XML);

							System.out.println("Candidature supprimée");
							return;
						}

					}
				}
			}
			throw new FactoryException("Ce projet n'existe pas ou plus");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Candidature candidature, Projet projet) throws FactoryException {
		try {
			File file = new File(InitializationServlet.ATT_PROJETS_XML);
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);

			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(projet.getNom())) {
					for (Candidature c : p.getCandidatures().getCandidature()) {
						if (c.getUtilisateur().getEmail().equals(candidature.getUtilisateur().getEmail()))
							c.setEtatCandidature("Valide");
						// Write
						JaxParser.marshal(listProjets, file, InitializationServlet.ATT_PROJETS_XML);
						System.out.println(listProjets);
						System.out.println("Candidature validée");
						return;
					}

				}
			}
			throw new FactoryException("Ce projet n'existe pas ou plus");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}

	}
}
