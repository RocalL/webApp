package services;

import java.util.Arrays;

import model.Candidature;

public class Candidatures {
	private Candidature[] candidatures;

	public Candidature[] getCandidature() {
		return candidatures;
	}

	public void setCandidature(Candidature[] candidatures) {
		this.candidatures = candidatures;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(candidatures);
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
		Candidatures other = (Candidatures) obj;
		if (!Arrays.equals(candidatures, other.candidatures))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Candidatures [candidatures=" + Arrays.toString(candidatures) + "]";
	}

}
