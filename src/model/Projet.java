package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import model.Candidatures;
@XmlType(propOrder={"image", "nom", "descriptif","deadLineCandidature","deadLineProjet","nbMaxCandidatures","candidatures"})
@XmlRootElement
public class Projet {
	private int nbMaxCandidatures;

	private String deadLineProjet;

	private String image;

	private String descriptif;

	private String deadLineCandidature;

	private String nom;

	private Candidatures candidatures;

	public int getNbMaxCandidatures() {
		return nbMaxCandidatures;
	}

	public void setNbMaxCandidatures(int nbMaxCandidatures) {
		this.nbMaxCandidatures = nbMaxCandidatures;
	}

	public String getDeadLineProjet() {
		return deadLineProjet;
	}

	public void setDeadLineProjet(String deadLineProjet) {
		this.deadLineProjet = deadLineProjet;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getDeadLineCandidature() {
		return deadLineCandidature;
	}

	public void setDeadLineCandidature(String deadLineCandidature) {
		this.deadLineCandidature = deadLineCandidature;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Candidatures getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(Candidatures candidatures) {
		this.candidatures = candidatures;
	}

	@Override
	public String toString() {
		return "Projet [nbMaxCandidatures=" + nbMaxCandidatures + ", deadLineProjet=" + deadLineProjet + ", image="
				+ image + ", descriptif=" + descriptif + ", deadLineCandidature=" + deadLineCandidature + ", nom=" + nom
				+ ", candidatures=" + candidatures + "]";
	}

	public void addCandidature(Candidature candidature) {
		if (this.candidatures.getCandidature() != null) {
			this.candidatures.addCandidature(candidature);
		} else {
			Candidatures c = new Candidatures();
			ArrayList<Candidature> list = new ArrayList<Candidature>();
			list.add(candidature);
			c.setCandidature(list);
			this.setCandidatures(c);
		}

	}
	
	
	public void deleteCandidature(String mail) {
		Candidatures listeCandidature = this.getCandidatures();
		Candidature candidatureToDelete = listeCandidature.getCandidatureByMail(mail);
		if(candidatureToDelete != null) {
			this.candidatures.deleteCandidature(candidatureToDelete);	
		}
		
	}
}