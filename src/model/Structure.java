package model;

public class Structure {
	private int ca;
	private Long siret;
	private String raisonSocial;

	public Structure(int ca, Long siret, String raisonSocial) {
		this.ca = ca;
		this.siret = siret;
		this.raisonSocial = raisonSocial;
	}

	public Structure() {
	}

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
	public String toString() {
		return "Structure [ca=" + ca + ", siret=" + siret + ", raisonSocial=" + raisonSocial + "]";
	}

}
