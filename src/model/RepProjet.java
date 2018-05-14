package model;

public class RepProjet
{
    private String website;

    private String devis;

    private String fichier;

    public String getWebsite ()
    {
        return website;
    }

    public void setWebsite (String website)
    {
        this.website = website;
    }

    public String getDevis ()
    {
        return devis;
    }

    public void setDevis (String devis)
    {
        this.devis = devis;
    }

    public String getFichier ()
    {
        return fichier;
    }

    public void setFichier (String fichier)
    {
        this.fichier = fichier;
    }

	@Override
	public String toString() {
		return "RepProjet [website=" + website + ", devis=" + devis + ", fichier=" + fichier + "]";
	}


}
	