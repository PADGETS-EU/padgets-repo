package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.User;

import org.apache.chemistry.opencmis.util.content.LoreIpsum;

import play.Logger;
import play.Play;
import play.libs.IO;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.results.Redirect;
import rest.conf.ClientFactory;
import rest.dto.CampaignDTO;
import rest.dto.CampaignListDTO;
import rest.dto.CommentDTO;
import rest.dto.FileUploadFormDTO;
import rest.dto.LongListDTO;
import rest.dto.MediaDTO;
import rest.dto.MediaListDTO;
import rest.dto.MessageDTO;
import rest.dto.MessageListDTO;
import rest.dto.PublishChannelDTO;
import rest.dto.PublishChannelListDTO;
import rest.dto.SMPAccountDTO;
import rest.dto.SMPAccountListDTO;
import rest.dto.SurveyDTO;
import rest.dto.UserDTO;
import rest.interfaces.CampaignAPI;
import rest.interfaces.MediaAPI;
import rest.interfaces.MessageAPI;
import rest.interfaces.SMPAccountAPI;
import rest.interfaces.UserAPI;
import util.StringUtil;

public class Application extends Controller {

	private static LoreIpsum lorem = new LoreIpsum();
	private static final String url = Play.configuration
			.getProperty("as-padgets.url") + "rest";
	private static ClientFactory clientFactory = new ClientFactory(url);
	private static UserAPI userAPI = clientFactory.createProxy(UserAPI.class);
	private static CampaignAPI campaignAPI = clientFactory
			.createProxy(CampaignAPI.class);
	private static SMPAccountAPI smpAccountAPI = clientFactory
			.createProxy(SMPAccountAPI.class);
	private static MessageAPI messageAPI = clientFactory
			.createProxy(MessageAPI.class);
	private static MediaAPI mediaAPI = clientFactory
			.createProxy(MediaAPI.class);

	@Before(unless = { "login", "authorize", "test" })
	static void checkAuthentification() {
		if (session.get("userSIGN") == null)
			login();
	}

	public static void test() {
		// List<Data> datas = userAPI.getData();
		// userAPI.sendData(datas);
	}

	public static void login() {
		render();
	}

	public static void logout() {
		session.remove("userSIGN");
		session.remove("userId");

		// TODO send logout event to the backend
		login();
	}

	public static void authorize(long userId, String userSIGN) {
		Logger.debug("");
		if (userId > 0 && !StringUtil.isEmptyOrWhitespace(userSIGN)) {
			session.put("userSIGN", userSIGN);
			session.put("userId", userId);
			index();
		} else {
			// TODO handle error
			renderText("error");
		}
	}

	public static void addNetwork(String network) {
		long userId = getSessionUserId();
		String redirectURL = "http://localhost:10000/profile";

		String redirect = smpAccountAPI.addSMPAccount(userId, network,
				redirectURL);

		try {
			if (!StringUtil.isEmptyOrWhitespace(redirect)
					&& redirect.startsWith("http"))
				throw new Redirect(redirect);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void removeNetwork(long accountId) {
		long userId = getSessionUserId();
		smpAccountAPI.removeSMPAccount(userId, accountId);

		profile();
	}

	public static void profile() {
		long userId = getSessionUserId();
		// update user
		UserDTO user = userAPI.getByUserId(userId);
		// User user = updateUser(userDTO);
		// get connected social media accounts
		SMPAccountListDTO accountsDTO = smpAccountAPI.getSMPAccounts(userId);
		List<SMPAccountDTO> accounts = accountsDTO.smpAccounts;
		// get all channels
		PublishChannelListDTO channelsDTO = smpAccountAPI.getPublishChannels(
				userId, null);
		List<PublishChannelDTO> channels = channelsDTO.channels;
		render(user, accounts, channels);
	}

	public static long getSessionUserId() {
		return new Long(session.get("userId")).longValue();
	}

	public static void index() {
		Logger.debug("");
		long userId = getSessionUserId();

		// get user and campaign
		UserDTO user = userAPI.getByUserId(userId);
		CampaignListDTO campaignListDTO = userAPI.getUsersCampaigns(userId);
		List<CampaignDTO> campaigns = campaignListDTO.campaigns;

		// update user in local db
		// User user = updateUser(userDTO);

		render(user, campaigns);
	}

	private static User updateUser(UserDTO userDTO) {
		// TODO
		User user = User.findByUserId(userDTO.uid);

		if (user == null) {
			user = new User();
		}
		// MapperSingelton.getInstance().map(userDTO, user);

		// TODO Do we need also information about user's social media accounts?
		user.save();
		return user;
	}

	public static void createCampaign() {
		editCampaignDetails(0);
	}

	public static void editCampaignDetails(long cid) {
		long id = getSessionUserId();

		PublishChannelListDTO allChannelsDTO = smpAccountAPI
				.getPublishChannels(id, null);
		PublishChannelListDTO selcetedChannelsDTO = campaignAPI
				.getChannels(cid);

		// the campaign manager should at least a facebook or blogger
		// account connected with padgets platform
		if (allChannelsDTO.count == 0)
			profile();

		List<PublishChannelDTO> allChannels = new ArrayList<PublishChannelDTO>();
		if (allChannelsDTO != null)
			allChannels = allChannelsDTO.channels;
		List<PublishChannelDTO> activeChannels = new ArrayList<PublishChannelDTO>();
		if (selcetedChannelsDTO != null)
			activeChannels = selcetedChannelsDTO.channels;

		CampaignDTO campaignDTO = new CampaignDTO();
		if (cid != 0) { // edit existing campaign, get it from backend
			campaignDTO = campaignAPI.get(cid);
		} else {
			// create dummy title
			campaignDTO.title = lorem.generateSentence(false);
		}

		allChannels.removeAll(activeChannels);

		render(id, campaignDTO, allChannels, activeChannels);
	}

	public static void saveCampaignDetails(CampaignDTO campaignDTO,
			String startdate, String enddate, long fitemId, long bitemId,
			List<Long> channelId) {

		if (startdate == null || startdate.equals("") || enddate == null
				|| enddate.equals("") || campaignDTO.title == null
				|| campaignDTO.title.equals("") || channelId == null) {
			// go back
			createCampaign();
		}
		long userId = getSessionUserId();

		if (campaignDTO.cid == 0) // new campaign
			campaignDTO = campaignAPI.create(userId, campaignDTO);

		// TODO choose proper date format!!!
		// DateFormat dF = DateFormat.getDateInstance(DateFormat.MEDIUM);
		// DateFormat dF = new SimpleDateFormat("dd-MM-yyyy");
		// campaignDTO.startdate = dF.parse(startdate).getTime() + "";
		// campaignDTO.enddate = dF.parse(enddate).getTime() +"";
		campaignDTO.startdate = startdate;
		campaignDTO.enddate = enddate;
		campaignDTO = campaignAPI.update(campaignDTO.cid, campaignDTO);

		List<Long> channels = new ArrayList<Long>();
		if (fitemId > 0)
			channels.add(new Long(fitemId));
		if (bitemId > 0)
			channels.add(new Long(bitemId));

		campaignDTO = campaignAPI.addChannelsIds(campaignDTO.cid,
				new LongListDTO(channelId));

		showCampaign(campaignDTO.cid);
	}

	public static void createMessage(long cid) {
		editMessage(cid, 0);
	}

	public static void editMessage(long cid, long mid) {
		long userId = getSessionUserId();
		// get campaign
		CampaignDTO campaignDTO = campaignAPI.get(cid);
		// get channels
		PublishChannelListDTO channelsDTO = campaignAPI.getChannels(cid);
		List<PublishChannelDTO> channels = channelsDTO.channels;
		// get or init message
		MessageDTO messageDTO = new MessageDTO();
		if (mid > 0)
			messageDTO = messageAPI.get(mid);
		else {
			// dummy content
			messageDTO.title = lorem.generateSentence(false);
			messageDTO.content = lorem.generateParagraphsPlainText(2, false);
		}
		render(campaignDTO, messageDTO, channels);
	}

	public static void saveMessage(MessageDTO messageDTO, long campaignId) {

		long userId = getSessionUserId();
		// TODO update information about published social media accounts
		String[] smps = { "facebook", "twitter", "blogger" };

		if (messageDTO.msgid == 0) // new campaign
			messageDTO = messageAPI.create(campaignId, messageDTO);
		else {
			// update
			messageDTO = messageAPI.updates(messageDTO.msgid, messageDTO);
		}

		showCampaign(campaignId);
	}

	public static void deleteMessage(long cid, long mid) {
		messageAPI.delete(mid);
		showCampaign(cid);
	}

	public static void createMedia(long cid) {
		editMedia(cid, 0);
	}

	public static void editMedia(long cid, long mid) {
		long userId = getSessionUserId();
		// get campaign
		CampaignDTO campaignDTO = campaignAPI.get(cid);
		// get accounts
		SMPAccountListDTO accountsDTO = smpAccountAPI.getSMPAccounts(userId);
		List<SMPAccountDTO> channels = accountsDTO.smpAccounts;
		// get or init message
		MediaDTO mediaDTO = new MediaDTO();
		if (mid > 0)
			mediaDTO = mediaAPI.get(mid);
		else { // dummy content
			mediaDTO.title = lorem.generateParagraphsPlainText(1, true);
		}

		render(campaignDTO, mediaDTO, channels);
	}

	public static void saveMedia(long campaignId, long mediaId,
			String description, File file) {
		FileUploadFormDTO uploadForm = new FileUploadFormDTO();
		if (file != null) {
			uploadForm.filedata = IO.readContent(file);
			uploadForm.filename = file.getName();
		}
		uploadForm.campainId = campaignId;
		uploadForm.mediaId = mediaId;
		uploadForm.description = description;
		// TODO manage SMPs

		mediaAPI.create(campaignId, uploadForm);
		showCampaign(campaignId);
	}

	public static void deleteMedia(long cid, long mid) {
		mediaAPI.delete(mid);
		showCampaign(cid);
	}

	public static void showCampaign(long cid) {
		// get campaign, messages and media
		CampaignDTO campaignDTO = campaignAPI.get(cid);
		MessageListDTO messageListDTO = campaignAPI.getMessages(cid);
		List<MessageDTO> messageList = messageListDTO.messages;
		Map<Long, List<CommentDTO>> comments = new HashMap<Long, List<CommentDTO>>();
		// get comments
		if (messageList != null)
			for (MessageDTO messageDTO : messageList) {
				comments.put(messageDTO.msgid,
						messageAPI.getComments(messageDTO.msgid).comments);
			}
		MediaListDTO mediaListDTO = campaignAPI.getMedias(cid);
		List<MediaDTO> mediaList = mediaListDTO.medias;
		
		List<SurveyDTO> surveyList = campaignAPI.getSurveys(cid).surveys;
		render(campaignDTO, messageList, mediaList, comments, surveyList);
	}

	public static void activateCampaign(long cid) {
		campaignAPI.activate(cid);
		showCampaign(cid);
	}

	public static void createSurvey(long cid) {
		SurveyDTO surveyDTO = campaignAPI.newSurvey(cid);
		editSurvey(cid, surveyDTO.sid);
	}

	public static void editSurvey(long cid, long sid) {

		SurveyDTO surveyDTO = campaignAPI.getSurvey(cid, sid);
		render(surveyDTO);
	}
	
	public static void resultsSurvey(long cid, long sid){
		SurveyDTO surveyDTO = campaignAPI.getSurvey(cid, sid);
		System.out.println(surveyDTO.sKey);
		render(surveyDTO);
	}
	//
	// public static void padgetView(String padgetID) {
	// String userSIGN = session.get("userSIGN");
	// WS.WSRequest req =
	// WS.url(Play.configuration.getProperty("oneopenapi.url") +
	// "getPadgetInfo");
	// req.setParameter("parameters.padgetID", padgetID);
	// req.setParameter("parameters.userSIGN", userSIGN);
	// req.setHeader("accept", "application/json");
	// WS.HttpResponse resp = req.get();
	// JsonElement element = resp.getJson();
	// Padget padget = new Gson().fromJson(element.getAsJsonObject(),
	// Padget.class);
	// render(padget);
	// }
	//
	// public static void publish(String padgetID) {
	//
	// String[] smps = { "facebook", "twitter", "blogger" };
	// List<String> networks = new LinkedList<String>();
	// for (String s : smps) {
	// if (request.params.get(s) != null) {
	// networks.add(s);
	// }
	// }
	// if (networks.size() == 0) {
	// padgetView(padgetID);
	// }
	// WSRequest req = WS.url(Play.configuration.getProperty("oneopenapi.url") +
	// "publish");
	// String userSIGN = session.get("userSIGN");
	// req.setParameter("parameters.userSIGN", userSIGN);
	// req.setParameter("parameters.padgetID", padgetID);
	// req.setParameter("networks", networks);
	//
	// WS.HttpResponse resp = req.get();
	//
	// index();
	// }
	//
	// public static void succesUpload(String fileName) {
	// render(fileName);
	// }
	//
	// public class PadgetsIDs {
	// public LinkedList<String> padgetList;
	//
	// public PadgetsIDs() {
	// padgetList = new LinkedList<String>();
	// }
	// }

}