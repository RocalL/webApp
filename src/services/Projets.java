package services;

import model.Projet;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Projets {
	private ArrayList<Projet> projet;

	public ArrayList<Projet> getProjet() {
		return projet;
	}

	public void setProjet(ArrayList<Projet> projet) {
		this.projet = projet;
	}

	@Override
	public String toString() {
		return "Projets [projet=" + projet + "]";
	}

}
