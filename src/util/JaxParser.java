package util;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

public class JaxParser {
	private static final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
	private static final List<String> arguments = runtimeMxBean.getInputArguments();
	private static final String CHEMIN = arguments.get(1);
	
    public static <T> T unmarshal(Class<T> cl, File f) throws JAXBException
    {
        return unmarshal(cl, new StreamSource(CHEMIN));
    }
	  public static <T> T unmarshal(Class<T> cl, Source s) throws JAXBException
	    {
	        JAXBContext ctx = JAXBContext.newInstance(cl);
	        Unmarshaller u = ctx.createUnmarshaller();
	        return u.unmarshal(s, cl).getValue();
	    }
	
    public static <T> void marshal(T obj, File f) throws JAXBException
    {
        JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(obj, System.out);
    }

}
