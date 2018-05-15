package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"dateCandidature", "utilisateur", "structure","repProjet"})
@XmlRootElement
public class Candidature {
	private Structure structure;

	private RepProjet repProjet;

	private String dateCandidature;

	private Utilisateur utilisateur;

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public RepProjet getRepProjet() {
		return repProjet;
	}

	public void setRepProjet(RepProjet repProjet) {
		this.repProjet = repProjet;
	}

	public String getDateCandidature() {
		return dateCandidature;
	}

	public void setDateCandidature(String dateCandidature) {
		this.dateCandidature = dateCandidature;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Candidature [structure=" + structure + ", repProjet=" + repProjet + ", dateCandidature="
				+ dateCandidature + ", utilisateur=" + utilisateur + "]";
	}

}