package model;

public class RepProjet {
	private String fichier;
	private String website;
	private String devis;

	public RepProjet(String fichier, String website, String devis) {
		this.fichier = fichier;
		this.website = website;
		this.devis = devis;
	}

	public RepProjet() {
	}

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDevis() {
		return devis;
	}

	public void setDevis(String devis) {
		this.devis = devis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((devis == null) ? 0 : devis.hashCode());
		result = prime * result + ((fichier == null) ? 0 : fichier.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		RepProjet other = (RepProjet) obj;
		if (devis == null) {
			if (other.devis != null)
				return false;
		} else if (!devis.equals(other.devis))
			return false;
		if (fichier == null) {
			if (other.fichier != null)
				return false;
		} else if (!fichier.equals(other.fichier))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RepProjet [fichier=" + fichier + ", website=" + website + ", devis=" + devis + "]";
	}

}
