package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import model.Candidature;

@XmlRootElement
public class Candidatures {
	private ArrayList<Candidature> candidature = new ArrayList<>();

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
	
	public void deleteCandidature(Candidature candidature) {
		//System.out.println(this.candidature.toString());
		this.candidature.remove(candidature);
		//System.out.println(this.candidature.toString());
	}
	
	public Candidature getCandidatureByMail(String mail) {
		for (Candidature c : candidature ) {
				if (c.getUtilisateur().getEmail().equals(mail))
					return c;
		}
		return null;
	}

}
