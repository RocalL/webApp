package model;

import java.util.concurrent.atomic.AtomicInteger;

import services.Candidatures;

public class Projet {
	private static final AtomicInteger idProjet = new AtomicInteger(0);
	private int nbMaxCandidatures;
	private String deadLineProjet;
	private String image;
	private String descriptif;
	private String deadLineCandidature;
	private String nom;
	private Candidatures candidatures;

	public Projet(int nbMaxCandidatures, String deadLineProjet, String image, String descriptif,
			String deadLineCandidature, String nom, Candidatures candidatures) {
		this.nbMaxCandidatures = nbMaxCandidatures;
		this.deadLineProjet = deadLineProjet;
		this.image = image;
		this.descriptif = descriptif;
		this.deadLineCandidature = deadLineCandidature;
		this.nom = nom;
		this.candidatures = candidatures;
	}

	public Projet() {

	}

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

	public static AtomicInteger getIdprojet() {
		return idProjet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidatures == null) ? 0 : candidatures.hashCode());
		result = prime * result + ((deadLineCandidature == null) ? 0 : deadLineCandidature.hashCode());
		result = prime * result + ((deadLineProjet == null) ? 0 : deadLineProjet.hashCode());
		result = prime * result + ((descriptif == null) ? 0 : descriptif.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + nbMaxCandidatures;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Projet other = (Projet) obj;
		if (candidatures == null) {
			if (other.candidatures != null)
				return false;
		} else if (!candidatures.equals(other.candidatures))
			return false;
		if (deadLineCandidature == null) {
			if (other.deadLineCandidature != null)
				return false;
		} else if (!deadLineCandidature.equals(other.deadLineCandidature))
			return false;
		if (deadLineProjet == null) {
			if (other.deadLineProjet != null)
				return false;
		} else if (!deadLineProjet.equals(other.deadLineProjet))
			return false;
		if (descriptif == null) {
			if (other.descriptif != null)
				return false;
		} else if (!descriptif.equals(other.descriptif))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (nbMaxCandidatures != other.nbMaxCandidatures)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Projet [nbMaxCandidatures=" + nbMaxCandidatures + ", deadLineProjet=" + deadLineProjet + ", image="
				+ image + ", descriptif=" + descriptif + ", deadLineCandidature=" + deadLineCandidature + ", nom=" + nom
				+ ", candidatures=" + candidatures + "]";
	}

}
