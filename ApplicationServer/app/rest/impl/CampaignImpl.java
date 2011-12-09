/**
 * 
 */
package rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.google.gson.JsonElement;

import models.Campaign;
import models.Media;
import models.Message;
import models.PublishChannel;
import models.Survey;
import play.Logger;
import play.Play;
import play.libs.WS;
import rest.conf.MapperSingelton;
import rest.dto.CampaignDTO;
import rest.dto.LongListDTO;
import rest.dto.MediaDTO;
import rest.dto.MediaListDTO;
import rest.dto.MessageDTO;
import rest.dto.MessageListDTO;
import rest.dto.PublishChannelDTO;
import rest.dto.PublishChannelListDTO;
import rest.dto.SurveyDTO;
import rest.dto.SurveyListDTO;
import rest.interfaces.CampaignAPI;
import utils.Padgets;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
public class CampaignImpl implements CampaignAPI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.CampaignAPI#create(long)
	 */
	@Override
	public CampaignDTO create(long userId) {
		Logger.debug("");
		Campaign campaign = new Campaign();
		campaign.cid = Padgets.generateNewId();
		campaign.userId = userId;
		campaign.creationdate = System.currentTimeMillis() + "";
		campaign.save();
		return campaign.getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.CampaignAPI#create(long, rest.dto.CampaignDTO)
	 */
	@Override
	public CampaignDTO create(long userId, CampaignDTO campaignDTO) {
		Logger.debug("");
		Campaign campaign = new Campaign();
		campaign.cid = Padgets.generateNewId();
		campaign.userId = userId;
		campaign.creationdate = System.currentTimeMillis() + "";
		campaign.save();
		campaignDTO.cid = campaign.cid; // TODO configure dozer correctly, to
										// NOT to overwrite campaign.cid
		MapperSingelton.getInstance().map(campaignDTO, campaign);
		campaign.save();
		return campaign.getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.CampaignAPI#get(long)
	 */
	@Override
	public CampaignDTO get(long campaignId) {
		Campaign campaign = Campaign.findByCampaignId(campaignId);
		return campaign.getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.CampaignAPI#updates(long, rest.dto.CampaignDTO)
	 */
	@Override
	public CampaignDTO update(long campaignId, CampaignDTO campaignDTO) {
		if (campaignDTO.cid != campaignId) // TODO throw meaningful exception
			return null;
		Campaign campaign = Campaign.findByCampaignId(campaignId);
		MapperSingelton.getInstance().map(campaignDTO, campaign);
		campaign.save();
		return campaign.getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.CampaignAPI#getMessages(long)
	 */
	@Override
	public MessageListDTO getMessages(long campaignId) {
		List<Message> messages = Message.findByCampaignId(campaignId);
		List<MessageDTO> dtos = new ArrayList<MessageDTO>();
		for (Message message : messages) {
			dtos.add(MapperSingelton.getInstance().map(message,
					MessageDTO.class));
		}
		return new MessageListDTO(dtos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.CampaignAPI#getMedias(long)
	 */
	@Override
	public MediaListDTO getMedias(long campaignId) {
		List<Media> medias = Media.findByCampaignId(campaignId);
		List<MediaDTO> dtos = new ArrayList<MediaDTO>();
		for (Media media : medias) {
			dtos.add(MapperSingelton.getInstance().map(media, MediaDTO.class));
		}
		return new MediaListDTO(dtos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.CampaignAPI#activate(long)
	 */
	@Override
	public void activate(long campaignId) {
		Campaign campaign = Campaign.findByCampaignId(campaignId);
		campaign.active = true;
		campaign.save();
		// TODO return something meaningful?
		// return campaign.getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.CampaignAPI#getChannels(long, java.util.List)
	 */
	@Override
	public PublishChannelListDTO getChannels(long campaignId) {
		Campaign campaign = Campaign.findByCampaignId(campaignId);
		if (campaign == null)
			return null;
		List<PublishChannelDTO> dtos = new ArrayList<PublishChannelDTO>();
		PublishChannel channel;
		for (Long channelId : campaign.channels) {
			channel = PublishChannel.findByChannelId(channelId);
			dtos.add(MapperSingelton.getInstance().map(channel,
					PublishChannelDTO.class));
		}
		return new PublishChannelListDTO(dtos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.CampaignAPI#addChannels(long, java.util.List)
	 */
	@Override
	public CampaignDTO addChannelsIds(long campaignId, LongListDTO channelsIds) {
		Campaign campaign = Campaign.findByCampaignId(campaignId);
		campaign.channels = new TreeSet<Long>(channelsIds.list);
		campaign.save();
		return campaign.getDTO();
	}

	@Override
	public SurveyDTO newSurvey(long campaignId) {
		Logger.debug("===================================");
		Survey survey = new Survey();
		survey.sid = Padgets.generateNewId();
		String surveyServerUrl = Play.configuration.getProperty("survey.url");
		//get survey key
		String createUrl = surveyServerUrl + "resources/createSurvey";
		JsonElement json = WS.url(createUrl).get().getJson();
		survey.sKey = json.getAsJsonObject().get("surveyKey").getAsString();
		survey.campaignId = campaignId;
		survey.editUrl = surveyServerUrl + "content/editSurvey.xhtml?key=" + survey.sKey;
		survey.voteUrl = surveyServerUrl + "content/vote.xhtml?key=" + survey.sKey;
		survey.resultsUrl = surveyServerUrl + "content/results.xhtml?key=" + survey.sKey;
		survey.save();
		return MapperSingelton.getInstance().map(survey, SurveyDTO.class);
	}

	@Override
	public SurveyDTO getSurvey(long campaignId, long surveyId) {
		return Survey.findBySurveyId(surveyId);
	}

	@Override
	public SurveyListDTO getSurveys(long campaignId) {
		List<Survey> surveys = Survey.findByCampaignId(campaignId);
		List<SurveyDTO> dtos = new ArrayList<SurveyDTO>();
		for (Survey survey : surveys) {
			dtos.add(MapperSingelton.getInstance().map(survey, SurveyDTO.class));
		}
		return new SurveyListDTO(dtos);
	}

}
