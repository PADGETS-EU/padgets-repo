/**
 * 
 */
package rest.conf;

import java.lang.annotation.Annotation;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.jboss.resteasy.annotations.DecorateTypes;
import org.jboss.resteasy.spi.interception.DecoratorProcessor;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@DecorateTypes({"text/*+xml", "application/*+xml", "application/*+json"})
public class PrettyProcessor implements DecoratorProcessor<Marshaller, Pretty>
{
   public Marshaller decorate(Marshaller target, Pretty annotation,
                 Class type, Annotation[] annotations, MediaType mediaType)
   {
      try {
		target.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	} catch (PropertyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return target;
   }
}
