package factory;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBException;

import exception.FactoryException;
import model.Utilisateur;
import model.Utilisateurs;
import util.JaxParser;

public class UtilisateurFactoryImpl implements UtilisateurFactory {
	public UtilisateurFactoryImpl() {
		
	}
	
	@Override
	public void create(Utilisateur utilisateur, String chemin) throws FactoryException {
		try {
			File file = new File(chemin);
			// Read
			Utilisateurs listUtilisateurs = JaxParser.unmarshal(Utilisateurs.class, file);
			for (Utilisateur u : listUtilisateurs.getUtilisateur()) {

				if (u.getEmail().equals(utilisateur.getEmail())) {
					//On considère le mail comme seul identifiant
					throw new FactoryException("Un utilisateur ayant votre mail existe déjà");
				}
			}
			utilisateur.setRole("user");
			listUtilisateurs.getUtilisateur().add(utilisateur);
			// Write
			JaxParser.marshal(listUtilisateurs, file);
			System.out.println(utilisateur);
			System.out.println("UtilisateurFactoryImpl écrit: ");
			System.out.println("ajoutée à la base de donnée");
		} catch (FactoryException | JAXBException e) {
			e.printStackTrace();
		}
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
