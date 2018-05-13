package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Candidature
{
	private static final AtomicInteger idCandidature = new AtomicInteger(0);
    private Structure structure;
    private Utilisateur utilisateur;
    private RepProjet repProjet;
    private String dateCandidature;

	public Candidature(Structure structure, Utilisateur utilisateur, RepProjet repProjet,
			String dateCandidature) {
		this.structure = structure;
		this.utilisateur = utilisateur;
		this.repProjet = repProjet;
		this.dateCandidature = dateCandidature;
	}
	public Candidature() {
		
	}
	public Structure getStructure() {
		return structure;
	}
	public void setStructure(Structure structure) {
		this.structure = structure;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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
	public static AtomicInteger getIdcandidature() {
		return idCandidature;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCandidature == null) ? 0 : dateCandidature.hashCode());
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
	@Override
	public String toString() {
		return "Candidature [structure=" + structure + ", utilisateur=" + utilisateur + ", repProjet=" + repProjet
				+ ", dateCandidature=" + dateCandidature + "]";
	}
    
    

}