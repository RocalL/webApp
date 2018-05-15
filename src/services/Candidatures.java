package services;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import model.Candidature;

@XmlRootElement
public class Candidatures {
	private ArrayList<Candidature> candidature;

	public ArrayList<Candidature> getCandidature() {
		return candidature;
	}

	public void setCandidature(ArrayList<Candidature> candidature) {
		this.candidature = candidature;
	}

	@Override
	public String toString() {
		return "Candidatures [candidature=" + candidature + "]";
	}

	public void addCandidature(Candidature candidature) {
		this.candidature.add(candidature);
		
	}

}
