/**
 * 
 */
package rest.impl;

import java.util.ArrayList;
import java.util.List;

import models.AuthData;
import models.PublishChannel;
import models.SMPAccount;
import play.Logger;
import rest.conf.MapperSingelton;
import rest.dto.PublishChannelDTO;
import rest.dto.PublishChannelListDTO;
import rest.dto.SMPAccountDTO;
import rest.dto.SMPAccountListDTO;
import rest.interfaces.SMPAccountAPI;
import utils.StringUtil;

import components.Authentication;

/**
 * @author Peter Neve & Lukasz Radziwonowicz
 * 
 */
public class SMPAccountImpl implements SMPAccountAPI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.UserAPI#addSMPAccount(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String addSMPAccount(long userId, String network, String redirectUrl) {
		Logger.debug("");
		AuthData authData = new AuthData(userId, network, redirectUrl, null);
		return Authentication.auth(authData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.UserAPI#removeSMPAccount(long, long)
	 */
	@Override
	public String removeSMPAccount(long userId, long accountId) {
		SMPAccount smpAccount = SMPAccount.findByAccountId(accountId);
		if (smpAccount != null && smpAccount.userId == userId) {
			// delete smp account
			smpAccount.delete();
			// delete all administrated pages
			List<PublishChannel> pages =
					PublishChannel.findByUserIdAndNetwork(userId, smpAccount.network);
			for (PublishChannel page : pages) {
				// TODO maybe we have to revoke access token on social media
				// platform
				page.delete();
			}
			return "success";
		}
		// TODO proper error msg
		return "error";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.UserAPI#getUsersSMPAccounts(java.lang.String)
	 */
	@Override
	public SMPAccountListDTO getSMPAccounts(long userId) {
		List<SMPAccount> accounts = SMPAccount.findByUserId(userId);
		List<SMPAccountDTO> dtos = new ArrayList<SMPAccountDTO>();
		for (SMPAccount a : accounts) {
			dtos.add(MapperSingelton.getInstance().map(a, SMPAccountDTO.class));
		}
		return new SMPAccountListDTO(dtos);
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see rest.interfaces.UserAPI#getAllAministratedPages(java.lang.String,
	// * java.lang.String)
	// */
	// @Override
	// public AdministratedPageListDTO getAllAdministratedPages(long userId,
	// String network) {
	// List<AdministratedPage> pages = new ArrayList<AdministratedPage>();
	// if (userId == 0 && StringUtil.isEmptyOrWhitespace(network))
	// return null;
	// // only user id set -> return all pages
	// if (StringUtil.isEmptyOrWhitespace(network))
	// pages = AdministratedPage.findByUserId(userId);
	// // both set -> return filtered pages
	// if (userId > 0 && !StringUtil.isEmptyOrWhitespace(network))
	// pages = AdministratedPage.findByUserIdAndNetwork(userId, network);
	//
	// List<AdministratedPageDTO> dtos = new ArrayList<AdministratedPageDTO>();
	// for (AdministratedPage page : pages) {
	// dtos.add(MapperSingelton.getInstance().map(page,
	// AdministratedPageDTO.class));
	// }
	// return new AdministratedPageListDTO(dtos);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.SMPAccountAPI#getPublishChannels(long,
	 * java.lang.String)
	 */
	@Override
	public PublishChannelListDTO getPublishChannels(long userId, String network) {
		List<PublishChannel> channels = new ArrayList<PublishChannel>();
		if (userId == 0 && StringUtil.isEmptyOrWhitespace(network))
			return null;
		// only user id set -> return all channels
		if (StringUtil.isEmptyOrWhitespace(network))
			channels = PublishChannel.findByUserId(userId);
		// both set -> return filtered pages
		if (userId > 0 && !StringUtil.isEmptyOrWhitespace(network))
			channels = PublishChannel.findByUserIdAndNetwork(userId, network);

		List<PublishChannelDTO> dtos = new ArrayList<PublishChannelDTO>();
		for (PublishChannel channel : channels) {
			dtos.add(MapperSingelton.getInstance().map(channel, PublishChannelDTO.class));
		}
		return new PublishChannelListDTO(dtos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.UserAPI#updatePage(long,
	 * rest.dto.AdministratedPageDTO)
	 */
	@Override
	public PublishChannelDTO updatePage(long userId, PublishChannelDTO publishChannelDTO) {
		if (publishChannelDTO.userId != userId) // TODO throw meaningful
												// exception
			return null;
		PublishChannel page = PublishChannel.findByChannelId(publishChannelDTO.pchid);
		MapperSingelton.getInstance().map(publishChannelDTO, page);
		page.save();
		return page.getPageDTO();
	}

}
