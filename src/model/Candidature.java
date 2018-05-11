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
    
    

}