package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import factory.CandidatureFactory;
import factory.CandidatureFactoryImpl;
import factory.ProjetFactory;
import factory.ProjetFactoryImpl;
import model.Candidature;
import model.Candidatures;
import model.Projet;
import model.Projets;

@WebService
public class WebServiceImpl implements service.WebService {
	private ProjetFactory projetFactory;
	private CandidatureFactory candidatureFactory;

	public WebServiceImpl() {
		this.projetFactory = new ProjetFactoryImpl();
		this.candidatureFactory = new CandidatureFactoryImpl();
	}

	@WebMethod
	public Projets getProjets() {
		return projetFactory.getProjets();
	}

	@WebMethod
	public Projet getOneProjet(@WebParam(name = "nom") @XmlElement(required = true) String nom) {
		return projetFactory.getOne(nom);
	}

	@Override
	public Candidatures getCandidatures(@WebParam(name = "nomProjet") @XmlElement(required = true) String nomProjet) {
		return candidatureFactory.getCandidatures(nomProjet);
	}

	@Override
	public Candidature getOneCandidature(String mail, String nomProjet) {
		return candidatureFactory.getOne(mail, nomProjet);
	}

	@Override
	public void addCandidature(@WebParam(name = "Candidature") @XmlElement(required = true) Candidature candidature,
			@WebParam(name = "ProjetName") @XmlElement(required = true) String projetName) {
		candidatureFactory.create(candidature, projetName);
	}

}