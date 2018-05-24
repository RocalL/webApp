package factory;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import exception.FactoryException;
import model.Candidature;
import model.Candidatures;
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
	public Candidature getOne(String userMail, String nomProjet, String chemin) throws FactoryException {
		try {
			File file = new File(chemin);
			// Read
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);
		
			
			//Parcours des projets
			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(nomProjet)) {
					//Parcours des candidats
					Candidatures c = p.getCandidatures();
					//System.out.println(c.toString());

					return c.getCandidatureByMail(userMail);				
				}
		} 
		}
			catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Candidature> getAll() throws FactoryException {
		// FAUT METTRE NOTRE CODE ICI
		return null;
	}

	@Override
	public void delete(Candidature candidature,Projet projet, String chemin) throws FactoryException {
		try {
			File file = new File(chemin);
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);
			Candidatures listCandidatures = new Candidatures();//JaxParser.unmarshal(Candidatures.class, file);
			
			Candidature candid = new Candidature();
			
			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(projet.getNom())) {
					for(Candidature c : p.getCandidatures().getCandidature()) {
						if(c.getUtilisateur().getEmail().equals(candidature.getUtilisateur().getEmail())) {
							System.out.println(c.toString());
							listCandidatures = p.getCandidatures();
							candid = c;
							
					}
					
					}
				}	
			}
			System.out.println(candid.toString());
			System.out.println(listCandidatures.getCandidature().size());
			listCandidatures.deleteCandidature(candid);
			System.out.println(listCandidatures.getCandidature().size());
			
			// Write
			 JaxParser.marshal(listProjets, file);
			 
			 System.out.println("Candidature supprimée");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}

	

	}

	@Override
	public void update(Candidature candidature,Projet projet, String chemin) throws FactoryException {
		try {
			File file = new File(chemin);
			Projets listProjets = JaxParser.unmarshal(Projets.class, file);

			for (Projet p : listProjets.getProjet()) {
				if (p.getNom().equals(projet.getNom())) {
					for(Candidature c : p.getCandidatures().getCandidature()) {
						if(c.getUtilisateur().getEmail().equals(candidature.getUtilisateur().getEmail()))
						c.setEtatCandidature("Valide");				
					}
					
				}	
			}
			
			// Write
			 JaxParser.marshal(listProjets, file);
			 System.out.println(listProjets);
			 System.out.println("Candidature validée");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}

	}

}
