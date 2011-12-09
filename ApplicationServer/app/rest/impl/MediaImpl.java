/**
 * 
 */
package rest.impl;

import java.io.ByteArrayInputStream;
import java.io.File;

import models.Campaign;
import models.Media;

import org.apache.commons.codec.digest.DigestUtils;

import components.Publisher;

import play.Play;
import play.libs.IO;
import rest.conf.MapperSingelton;
import rest.dto.FileUploadFormDTO;
import rest.dto.MediaDTO;
import rest.interfaces.MediaAPI;
import utils.Padgets;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
public class MediaImpl implements MediaAPI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.MediaAPI#create(long)
	 */
	@Override
	public MediaDTO create(long campaignId) {
		Campaign campaign = Campaign.findByCampaignId(campaignId);
		if (campaign == null) // campaign does not exists;
			return null; // TODO proper error handling

		Media media = new Media();
		media.mid = Padgets.generateNewId();
		media.campaignId = campaign.cid;
		media.save();
		return media.getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.MediaAPI#create(long, rest.dto.MediaDTO)
	 */
	@Override
	public MediaDTO create(long campaignId, MediaDTO mediaDTO) {
		Campaign campaign = Campaign.findByCampaignId(campaignId);
		if (campaign == null) // campaign does not exists;
			return null; // TODO proper error handling

		Media media = new Media();
		media.mid = Padgets.generateNewId();
		media.campaignId = campaign.cid;
		media.save();
		mediaDTO.medid = media.mid;
		MapperSingelton.getInstance().map(mediaDTO, media);
		media.save();
		return media.getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.MediaAPI#create(long, rest.dto.FileUploadFormDTO)
	 */
	@Override
	public MediaDTO create(long campaignId, FileUploadFormDTO form) {
		if(form.filedata == null && form.mediaId == 0)
			return null;
		
		Campaign campaign = Campaign.findByCampaignId(form.campainId);
		
		// TODO manage SMPs
		// String[] targets = form.targetSMPs.substring(1,
		// form.targetSMPs.length() - 1).split(",");
		
		Media media = new Media();
		media.mid = Padgets.generateNewId();
		
		if(form.mediaId > 0) // update existing object
			media = Media.findByMediaId(form.mediaId);
		
		media.campaignId = campaign.cid;
		media.title = form.description;
		media.timestamp = System.currentTimeMillis();
		media.save();
		
		if (form.filedata != null) {
			// get md5 for file, unique id
			String md5 = DigestUtils.md5Hex(form.getFileData());

			// create directory for file
			File out =
					Play.getFile("public/images/" + md5.substring(0, 1) + "/" + md5.substring(1, 2));
			out.mkdirs();
			// // create tmp dir
			// File dir = Play.getFile("public/images/tmp");
			// dir.mkdir();
			// // create tmp file
			// File tmpFile = new File(dir, "test.jpg");
			// create local file
			md5 = md5 + form.filename.substring(form.filename.lastIndexOf("."));
			File newFile = new File(out, md5);
			// save content in local file
			ByteArrayInputStream stream = new ByteArrayInputStream(form.filedata);
			IO.write(stream, newFile);
			media.path = newFile.getPath();
			media.url = Media.createUrl(md5);
			media.type = Padgets.getMediaType(form.filename);
			media.save();
			
			Publisher.publish(campaign, media);
		}
		return media.getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.MediaAPI#get(long)
	 */
	@Override
	public MediaDTO get(long mediaId) {
		return Media.findByMediaId(mediaId).getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.MediaAPI#updates(long, rest.dto.MediaDTO)
	 */
	@Override
	public MediaDTO updates(long mediaId, MediaDTO mediaDTO) {
		if (mediaDTO.medid != mediaId)
			return null; // TODO proper error handling
		Media media = Media.findByMediaId(mediaId);
		MapperSingelton.getInstance().map(mediaDTO, media);
		media.save();
		return media.getDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.MediaAPI#delete(long)
	 */
	@Override
	public void delete(long mediaId) {
		Media media = Media.findByMediaId(mediaId);
		media.delete();
	}

}
