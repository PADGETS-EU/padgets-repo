/**
 * 
 */
package rest.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import rest.conf.Pretty;
import rest.dto.PublishChannelDTO;
import rest.dto.PublishChannelListDTO;
import rest.dto.SMPAccountListDTO;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
@Path("/padgetsuser")
@Produces({ "application/xml", "application/json" })
public interface SMPAccountAPI {
	/**
	 * Connects user's Social Media account to his/her profile.<br>
	 * <br>
	 * 
	 * Authorization Flow<br>
	 * 1. Send a request to /rest/padgetsuser/{uid}/smp/add with all parameters<br>
	 * 2. Use a URL returned by the response to redirect the user<br>
	 * 3. Wait while user is authorizing PADGETS platform<br>
	 * 4. After successful authorization Application Server redirects user to
	 * the page specified in redirectURL parameter<br>
	 * 
	 * @param userId
	 *            user id
	 * @param network
	 *            name of the Social Media Platform ("facebook", "twitter",
	 *            "blogger")
	 * @param redirectURL
	 *            after successful authorization, the user is directed to the
	 *            page specified by this parameter, is set by Front End
	 * @return a URL which is used by Front End to direct the user to
	 *         authentication page
	 */
	@GET
	@Path("/{uid}/smp/add")
	@Pretty
	public String addSMPAccount(@PathParam("uid") long userId,
			@QueryParam("network") String network, @QueryParam("redirectUrl") String redirectUrl);

	/**
	 * Disconnects/removes Social Media account from user's Padgets account
	 * 
	 * @param userId
	 * @param accountId
	 * @return
	 */
	@DELETE
	@Path("/{uid}/smp/{aid}/remove")
	@Pretty
	public String removeSMPAccount(@PathParam("uid") long userId, @PathParam("aid") long accountId);

	// /**
	// * Returns a list of pages/blogs administrated by the user on specific
	// * Social Media Platform
	// *
	// * @param userId
	// * @param network
	// * name of the Social Media Platform ("facebook", "twitter",
	// * "blogger"), not required
	// * @return
	// */
	// @GET
	// @Path("/{uid}/smp/pages")
	// @Pretty
	// // @Wrapped(element = "administratedpages")
	// public AdministratedPageListDTO
	// getAllAdministratedPages(@PathParam("uid") long userId,
	// @QueryParam("network") String network);

	/**
	 * Returns a list of pages/blogs administrated by the user on specific
	 * Social Media Platform
	 * 
	 * @param userId
	 * @param network
	 *            name of the Social Media Platform ("facebook", "twitter",
	 *            "blogger"), not required
	 * @return
	 */
	@GET
	@Path("/{uid}/smp/channels")
	@Pretty
	// @Wrapped(element = "channels")
	public PublishChannelListDTO getPublishChannels(@PathParam("uid") long userId,
			@QueryParam("network") String network);

	/**
	 * Updates user object
	 * 
	 * @param userDTO
	 * @return
	 */
	@PUT
	@Path("/{uid}/smp/page")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public PublishChannelDTO updatePage(@PathParam("uid") long userId,
			PublishChannelDTO publishChannelDTO);

	/**
	 * Returns a list of Social Media accounts associated/connected with user
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("/{uid}/smps")
	@Pretty
	public SMPAccountListDTO getSMPAccounts(@PathParam("uid") long userId);
}
