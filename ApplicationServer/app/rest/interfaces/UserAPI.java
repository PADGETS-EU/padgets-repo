/**
 * 
 */
package rest.interfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import rest.conf.Pretty;
import rest.dto.CampaignListDTO;
import rest.dto.Data;
import rest.dto.UserDTO;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
@Path("/padgetsuser")
@Produces({ "application/xml", "application/json" })
public interface UserAPI {

	/**
	 * Saves/creates user object in database
	 * 
	 * @param userModel
	 * @return
	 */
	@POST
	@Path("/")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public UserDTO create(UserDTO userDTO);

	/**
	 * Returns user object.
	 * 
	 * @param userId
	 * @return
	 */
	@GET
	@Path("/{uid}")
	@Pretty
	public UserDTO getByUserId(@PathParam("uid") long userId);

	/**
	 * Updates user object
	 * 
	 * @param userDTO
	 * @return
	 */
	@PUT
	@Path("/{uid}")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public UserDTO updates(@PathParam("uid") long userId, UserDTO userDTO);

	@GET
	@Path("/{uid}/campaigns")
	@Pretty
	public CampaignListDTO getUsersCampaigns(@PathParam("uid") long userId);

	@GET
	@Path("/datas")
	@Pretty
	@Wrapped(element = "datas")
	public List<Data> getData();
	
	@POST
	@Path("/datas")
	@Pretty
//	@Wrapped(element = "datas")
	@Consumes({ "application/xml", "application/json" })
	public void sendData(List<Data> datas);
}
