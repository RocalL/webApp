package model;

public class RepProjet {
	private int delais;
	private String fichier;
	private String liens;
	private String devis;

	public RepProjet(int delais, String fichier, String liens, String devis) {
		this.delais = delais;
		this.fichier = fichier;
		this.liens = liens;
		this.devis = devis;
	}

	public RepProjet() {
	}

	public int getDelais() {
		return delais;
	}

	public void setDelais(int delais) {
		this.delais = delais;
	}

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public String getLiens() {
		return liens;
	}

	public void setLiens(String liens) {
		this.liens = liens;
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
		result = prime * result + delais;
		result = prime * result + ((devis == null) ? 0 : devis.hashCode());
		result = prime * result + ((fichier == null) ? 0 : fichier.hashCode());
		result = prime * result + ((liens == null) ? 0 : liens.hashCode());
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
		if (delais != other.delais)
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
		if (liens == null) {
			if (other.liens != null)
				return false;
		} else if (!liens.equals(other.liens))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RepProjet [delais=" + delais + ", fichier=" + fichier + ", liens=" + liens + ", devis=" + devis + "]";
	}

}
