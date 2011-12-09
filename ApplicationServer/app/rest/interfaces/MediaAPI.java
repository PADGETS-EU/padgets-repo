/**
 * 
 */
package rest.interfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import rest.conf.Pretty;
import rest.dto.PublishChannelDTO;
import rest.dto.PublishChannelListDTO;
import rest.dto.CampaignDTO;
import rest.dto.Data;
import rest.dto.FileUploadFormDTO;
import rest.dto.MediaDTO;
import rest.dto.MessageDTO;
import rest.dto.SMPAccountListDTO;
import rest.dto.UserDTO;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
@Path("/padgetsmedia")
@Produces({ "application/xml", "application/json" })
public interface MediaAPI {

	/**
	 * Saves/creates media object in database
	 * 
	 * @param campaignId
	 * @return
	 */
	@POST
	@Path("/")
	@Pretty
	public MediaDTO create(@QueryParam("cid") long campaignId);
	
	/**
	 * Saves/creates media object in database
	 * 
	 * @param campaignId
	 * @param mediaDTO
	 * @return
	 */
	@POST
	@Path("/")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public MediaDTO create(@QueryParam("cid") long campaignId, MediaDTO mediaDTO);
	
	/**
	 * Uploads/saves/creates media object in database<br/>
	 * POST request of type "{@link http://www.ietf.org/rfc/rfc2388.txt}multipart/form-data"
	 * 
	 * @param campaignId
	 * @param form
	 * @return
	 */
	@POST
	@Path("/")
	@Pretty
	@Consumes("multipart/form-data")
	public MediaDTO create(@QueryParam("cid") long campaignId, @MultipartForm FileUploadFormDTO form);	
	
	/**
	 * Return media object
	 * 
	 * @param mediaId
	 * @return
	 */
	@GET
	@Path("/{mid}")
	@Pretty
	public MediaDTO get(@PathParam("mid") long mediaId);
	
	/**
	 * Updates media object
	 * 
	 * @param mediaId
	 * @param mediaDTO
	 * @return
	 */
	@PUT
	@Path("/{mid}")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public MediaDTO updates(@PathParam("mid") long mediaId, MediaDTO mediaDTO);
	
	/**
	 * Deletes media object
	 * 
	 * @param mediaId
	 * @return
	 */
	@DELETE
	@Path("/{mid}")
	@Pretty
	public void delete(@PathParam("mid") long mediaId);
}
