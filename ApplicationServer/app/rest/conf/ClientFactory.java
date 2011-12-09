/**
 * 
 */
package rest.conf;

import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;

import rest.interfaces.UserAPI;

/**
 * @author Peter Neve & Lukasz Radziwonowicz
 * 
 */
public class ClientFactory {

	private String applicationUrl;
	private ClientExecutor clientExecutor;

	/* cached instances */
	private UserAPI userAPI;
	
	@SuppressWarnings("unchecked")
	public ClientFactory(final String applicationUrl) {
		this.applicationUrl = applicationUrl;

		this.clientExecutor = new ApacheHttpClientExecutor() {
			@Override
			public ClientResponse execute(ClientRequest request) throws Exception {
				request.header("Authorization", "DopJ7gQ9NqGek7sQRcRwmBlH4DwGem");
				return super.execute(request);
			}
		};
	}

	public UserAPI getUserAPI() {
		if (userAPI == null) {
			userAPI = createProxy(UserAPI.class);
		}
		return userAPI;
	}	

	/**
	 * Creates client using RESTEasy proxies generated from annotated
	 * interfaces.
	 * 
	 * @param clazz
	 *            annotated interface of desired proxy client
	 * @return proxy client
	 */
	public <E> E createProxy(Class<E> clazz) {
		return ProxyFactory.create(clazz, applicationUrl, clientExecutor);
	}

}
