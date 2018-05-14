package model;

public class Structure {
	private int ca;

	private Long siret;

	private String raisonSocial;

	public int getCa() {
		return ca;
	}

	public void setCa(int ca) {
		this.ca = ca;
	}

	public Long getSiret() {
		return siret;
	}

	public void setSiret(Long siret) {
		this.siret = siret;
	}

	public String getRaisonSocial() {
		return raisonSocial;
	}

	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ca;
		result = prime * result + ((raisonSocial == null) ? 0 : raisonSocial.hashCode());
		result = prime * result + ((siret == null) ? 0 : siret.hashCode());
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
		Structure other = (Structure) obj;
		if (ca != other.ca)
			return false;
		if (raisonSocial == null) {
			if (other.raisonSocial != null)
				return false;
		} else if (!raisonSocial.equals(other.raisonSocial))
			return false;
		if (siret == null) {
			if (other.siret != null)
				return false;
		} else if (!siret.equals(other.siret))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Structure [ca=" + ca + ", siret=" + siret + ", raisonSocial=" + raisonSocial + "]";
	}

}
