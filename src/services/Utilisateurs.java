package services;

import java.util.Arrays;

import model.Utilisateur;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Utilisateurs {
	private Utilisateur[] utilisateur;

	public Utilisateur[] getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur[] utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(utilisateur);
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
		Utilisateurs other = (Utilisateurs) obj;
		if (!Arrays.equals(utilisateur, other.utilisateur))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utilisateurs [utilisateur=" + Arrays.toString(utilisateur) + "]";
	}

}