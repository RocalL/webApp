package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"dateCandidature","etatCandidature", "utilisateur", "structure","repProjet"})
@XmlRootElement
public class Candidature {
	private Structure structure;

	private RepProjet repProjet;

	private String dateCandidature;
	
	private String etatCandidature;

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
	
	public String getEtatCandidature() {
		return etatCandidature;
	}

	public void setEtatCandidature(String etatCandidature) {
		this.etatCandidature = etatCandidature;
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
				+ dateCandidature + ", etatCandidature=" + etatCandidature +", utilisateur=" + utilisateur + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCandidature == null) ? 0 : dateCandidature.hashCode());
		result = prime * result + ((etatCandidature == null) ? 0 : etatCandidature.hashCode());
		result = prime * result + ((repProjet == null) ? 0 : repProjet.hashCode());
		result = prime * result + ((structure == null) ? 0 : structure.hashCode());
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidature other = (Candidature) obj;
		if (dateCandidature == null) {
			if (other.dateCandidature != null)
				return false;
		} else if (!dateCandidature.equals(other.dateCandidature))
			return false;
		if (etatCandidature == null) {
			if (other.etatCandidature != null)
				return false;
		} else if (!etatCandidature.equals(other.etatCandidature))
			return false;
		if (repProjet == null) {
			if (other.repProjet != null)
				return false;
		} else if (!repProjet.equals(other.repProjet))
			return false;
		if (structure == null) {
			if (other.structure != null)
				return false;
		} else if (!structure.equals(other.structure))
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}

}