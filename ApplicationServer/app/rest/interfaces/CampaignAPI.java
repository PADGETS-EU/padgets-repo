/**
 * 
 */
package rest.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import rest.conf.Pretty;
import rest.dto.CampaignDTO;
import rest.dto.LongListDTO;
import rest.dto.MediaListDTO;
import rest.dto.MessageListDTO;
import rest.dto.PublishChannelListDTO;
import rest.dto.SurveyDTO;
import rest.dto.SurveyListDTO;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
@Path("/padgetscampaign")
@Produces({ "application/xml", "application/json" })
public interface CampaignAPI {

	/**
	 * Saves/creates campaign object in database
	 * 
	 * @param userId
	 * @return
	 */
	@POST
	@Path("/")
	@Pretty
	public CampaignDTO create(@QueryParam("userId") long userId);

	/**
	 * Saves/creates campaign object in database
	 * 
	 * @param userId
	 * @return
	 */
	@POST
	@Path("/")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public CampaignDTO create(@QueryParam("userId") long userId,
			CampaignDTO campaignDTO);

	/**
	 * Returns campaign object
	 * 
	 * @param campaignDTO
	 * @return
	 */
	@GET
	@Path("/{cid}")
	@Pretty
	public CampaignDTO get(@PathParam("cid") long campaignId);

	/**
	 * Updates campaign object
	 * 
	 * @param campaignDTO
	 * @return
	 */
	@PUT
	@Path("/{cid}")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public CampaignDTO update(@PathParam("cid") long campaignId,
			CampaignDTO campaignDTO);

	/**
	 * Returns list of messages for a campaign
	 * 
	 * @param campaignId
	 * @return
	 */
	@GET
	@Path("/{cid}/messages")
	@Pretty
	public MessageListDTO getMessages(@PathParam("cid") long campaignId);

	/**
	 * Returns list of messages for a campaign
	 * 
	 * @param campaignId
	 * @return
	 */
	@GET
	@Path("/{cid}/medias")
	@Pretty
	public MediaListDTO getMedias(@PathParam("cid") long campaignId);

	/**
	 * Activates a campaign
	 * 
	 * @param campaignId
	 * @return
	 */
	@PUT
	@Path("/{cid}/activate")
	@Pretty
	public void activate(@PathParam("cid") long campaignId);

	/**
	 * Gets publishing channels connected with the campaign.
	 * 
	 * @param campaignId
	 * @return
	 */
	@GET
	@Path("/{cid}/channels")
	@Pretty
	public PublishChannelListDTO getChannels(@PathParam("cid") long campaignId);

	/**
	 * Adds publishing channels to the campaign. Connects Social Media
	 * pages/blogs with campaign
	 * 
	 * @param campaignId
	 * @param channels
	 *            a list of channels id
	 * @return
	 */
	@PUT
	@Path("/{cid}/channels")
	@Pretty
	@Consumes({ "application/xml", "application/json" })
	public CampaignDTO addChannelsIds(@PathParam("cid") long campaignId,
			LongListDTO channelsIdsDTO);
	
	/**
	 * Returns survey
	 * 
	 * @param surveyId
	 * @param campaignId	 
	 * @return
	 */
	@POST
	@Path("/{cid}/survey")
	@Pretty
	public SurveyDTO getSurvey(@PathParam("cid") long campaignId, @QueryParam("sid") long surveyId);
	
	/**
	 * Creates survey
	 * 
	 * @param campaignId	 
	 * @return
	 */
	@POST
	@Path("/{cid}/survey")
	@Pretty
	public SurveyDTO newSurvey(@PathParam("cid") long campaignId);
	
	/**
	 * Returns surveys
	 * 
	 * @param campaignId	 
	 * @return
	 */
	@POST
	@Path("/{cid}/surveys")
	@Pretty
	public SurveyListDTO getSurveys(@PathParam("cid") long campaignId);
}
