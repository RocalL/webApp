package model;

public class RepProjet {
	private int delaisPropose;

	private String website;

	private String devis;

	private String fichier;

	public int getDelaisPropose() {
		return delaisPropose;
	}

	public void setDelaisPropose(int delaisPropose) {
		this.delaisPropose = delaisPropose;
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

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + delaisPropose;
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
		if (delaisPropose != other.delaisPropose)
			return false;
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
		return "RepProjet [delaisPropose=" + delaisPropose + ", website=" + website + ", devis=" + devis + ", fichier="
				+ fichier + "]";
	}

}
