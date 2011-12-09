/**
 * 
 */
package rest.conf;

/**
 * @author Peter Neve & Lukasz Radziwonowicz
 *
 */
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JaxbJacksonObjectMapper extends ObjectMapper {

 public JaxbJacksonObjectMapper() {
  final AnnotationIntrospector introspector
      = new JaxbAnnotationIntrospector();
  super.getDeserializationConfig()
       .appendAnnotationIntrospector(introspector);
  super.getSerializationConfig()
       .appendAnnotationIntrospector(introspector);
    }

}