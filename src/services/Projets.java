package services;

import model.Projet;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Projets
{
    private Projet[] projets;

    public Projet[] getProjets ()
    {
        return projets;
    }

    public void setProjets (Projet[] projets)
    {
        this.projets = projets;
    }

    @Override
    public String toString()
    {
        return "Projets [projets = "+projets+"]";
    }
    public Projet getProjetById(int id) {
    	for(int i=0;i<projets.length;i++) {
    		if (projets[i].getIdprojet() == id) {
    			return projets[i];
    		}
    	}
		return null;
    }
}
	