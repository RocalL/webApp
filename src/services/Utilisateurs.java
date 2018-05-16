package services;

import java.util.ArrayList;
import model.Utilisateur;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Utilisateurs {
	private ArrayList<Utilisateur> utilisateurs;

	public ArrayList<Utilisateur> getUtilisateur() {
		return utilisateurs;
	}

	public void setUtilisateur(ArrayList<Utilisateur> utilisateur) {
		this.utilisateurs = utilisateur;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((utilisateurs == null) ? 0 : utilisateurs.hashCode());
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
		if (utilisateurs == null) {
			if (other.utilisateurs != null)
				return false;
		} else if (!utilisateurs.equals(other.utilisateurs))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Utilisateurs [utilisateurs=" + utilisateurs + "]";
	}

	public void addUtilisateur(Utilisateur utilisateur) {
		this.utilisateurs.add(utilisateur);
	}

}