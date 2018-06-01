package util;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * <b>Classe générique pour lire et écrire sur le fichier XML
 *
 */
public class JaxParser {
	
    /**
     * @param Classe de l'objet root
     * @param Fichier de la base de données
     * @return une instance de l'objet construit à partir du parsing
     * @throws JAXBException
     */
    public static <T> T unmarshal(Class<T> cl, File f) throws JAXBException
    {
        return unmarshal(cl, new StreamSource(f));
    }
	  /**
     * @param Classe de l'objet root
     * @param Source du fichier de la base de données
     * @return une instance de l'objet construit à partir du parsing
     * @throws JAXBException
	 */
	public static <T> T unmarshal(Class<T> cl, Source s) throws JAXBException
	    {
	        JAXBContext ctx = JAXBContext.newInstance(cl);
	        Unmarshaller u = ctx.createUnmarshaller();
	        return u.unmarshal(s, cl).getValue();
	    }
	
    /**
     * @param Instance de l'objet à écrire
     * @param Fichier de la base de données
     * @param schemaLocation Chemin du XSD pour écrire le lien dans le XML
     * @throws JAXBException
     */
    public static <T> void marshal(T obj, File f, String schemaLocation) throws JAXBException
    {
    	schemaLocation = schemaLocation.substring(schemaLocation.lastIndexOf('/')+1, schemaLocation.length() );
    	schemaLocation = schemaLocation.substring(0, schemaLocation.lastIndexOf('.')) + ".xsd";
    	
        JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, schemaLocation);
        m.marshal(obj, f);
    }
}
