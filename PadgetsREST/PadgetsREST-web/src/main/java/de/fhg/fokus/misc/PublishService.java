/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.misc;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import javax.ejb.Stateless;



/**
 *
 * @author Hannes Gorges
 */
@Stateless
public class PublishService {
private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://rapidminersrv.aegean.gr:8080/padgets/publish";

    public PublishService() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI);
    }

    public void publishMessage(Integer messageid) throws UniformInterfaceException {
        WebResource resource = webResource.queryParam("idMessage", messageid.toString());
        resource.get(String.class);
    }

    public void close() {
        client.destroy();
    }
    
}
