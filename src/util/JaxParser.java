package util;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

public class JaxParser {
	
    public static <T> T unmarshal(Class<T> cl, File f) throws JAXBException
    {
        return unmarshal(cl, new StreamSource(f));
    }
	  public static <T> T unmarshal(Class<T> cl, Source s) throws JAXBException
	    {
	        JAXBContext ctx = JAXBContext.newInstance(cl);
	        Unmarshaller u = ctx.createUnmarshaller();
	        return u.unmarshal(s, cl).getValue();
	    }
	
    public static <T> void marshal(T obj, File f, String schemaLocation) throws JAXBException
    {
    	schemaLocation = changeExtension(schemaLocation,".xsd");
        JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, schemaLocation);
        m.marshal(obj, f);
    }
    public static String changeExtension(String schemaLocation, String newExtension) {
    	  int i = schemaLocation.lastIndexOf('.');
    	  String name = schemaLocation.substring(0,i);
    	  return name + newExtension;
    	}

}
