/**
 * 
 */
package rest.conf;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * @author Peter Neve & Lukasz Radziwonowicz
 * 
 */
// Customized {@code ContextResolver} implementation to pass ObjectMapper to use
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {
	private ObjectMapper mapper;

	public JacksonContextResolver() throws Exception {
		this.mapper = new ObjectMapper();
		this.mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// Jackson configuration, pretty printing
		this.mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);

//		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
//		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
//		AnnotationIntrospector introspector =
//				new AnnotationIntrospector.Pair(primary, secondary);
//		// make deserializer use JAXB annotations (only)
//		this.mapper.getDeserializationConfig().appendAnnotationIntrospector(introspector);
//		// make serializer use JAXB annotations (only)
//		this.mapper.getSerializationConfig().appendAnnotationIntrospector(introspector);
	}

	public ObjectMapper getContext(Class<?> objectType) {
		return mapper;
	}
}