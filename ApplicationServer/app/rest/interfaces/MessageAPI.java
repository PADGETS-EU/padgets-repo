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

import rest.conf.Pretty;
import rest.dto.CommentListDTO;
import rest.dto.PublishChannelDTO;
import rest.dto.PublishChannelListDTO;
import rest.dto.CampaignDTO;
import rest.dto.Data;
import rest.dto.MessageDTO;
import rest.dto.SMPAccountListDTO;
import rest.dto.UserDTO;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
@Path("/padgetsmessage")
@Produces({ "application/xml", "application/json" })
public interface MessageAPI {

	/**
	 * Saves/creates message object in database
	 * 
	 * @param campaignId
	 * @return
	 */
	@POST
	@Path("/")
	@Pretty
	public MessageDTO create(@QueryParam("cid") long campaignId);
	
	/**
	 * Saves/creates message object in database
	 * 
	 * @param campaignId
	 * @param messageDTO
	 * @return
	 */
	@POST
	@Path("/")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public MessageDTO create(@QueryParam("cid") long campaignId, MessageDTO messageDTO);
	
	/**
	 * Return message object
	 * 
	 * @param campaignDTO
	 * @return
	 */
	@GET
	@Path("/{mid}")
	@Pretty
	public MessageDTO get(@PathParam("mid") long messageId);
	
	/**
	 * Updates message object
	 * 
	 * @param messageId
	 * @param messageDTO
	 * @return
	 */
	@PUT
	@Path("/{mid}")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public MessageDTO updates(@PathParam("mid") long messageId, MessageDTO messageDTO);
	
	/**
	 * Deletes message object
	 * 
	 * @param messageId
	 * @return
	 */
	@DELETE
	@Path("/{mid}")
	@Pretty
	public void delete(@PathParam("mid") long messageId);
	
	/**
	 * Return message object
	 * 
	 * @param campaignDTO
	 * @return
	 */
	@GET
	@Path("/{mid}/comments")
	@Pretty
	public CommentListDTO getComments(@PathParam("mid") long messageId);
}
