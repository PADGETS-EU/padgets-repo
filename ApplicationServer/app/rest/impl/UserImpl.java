/**
 * 
 */
package rest.impl;

import java.util.ArrayList;
import java.util.List;

import models.Campaign;
import models.User;
import rest.conf.MapperSingelton;
import rest.dto.PublishChannelDTO;
import rest.dto.CampaignDTO;
import rest.dto.CampaignListDTO;
import rest.dto.Data;
import rest.dto.UserDTO;
import rest.interfaces.UserAPI;
import utils.Padgets;

/**
 * @author Peter Neve & Lukasz Radziwonowicz
 * 
 */
public class UserImpl implements UserAPI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.UserAPI#create(models.User)
	 */
	@Override
	public UserDTO create(UserDTO userDTO) {
		// init useer object
		User user = new User();
		user.uid = Padgets.generateNewId();
		user.save();
		user.userSIGN = "testing";
		userDTO.uid = user.uid;
		// map fileds from DTO
		MapperSingelton.getInstance().map(userDTO, user);
		// save in db
		user.save();
		System.out.println("user.userSIGN: " + user.userSIGN);
		// generate DTO
		return user.getUserDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.UserAPI#getUserById(java.lang.String)
	 */
	@Override
	public UserDTO getByUserId(long userId) {
		return User.findByUserId(userId).getUserDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.UserAPI#updates(rest.dto.UserDTO)
	 */
	@Override
	public UserDTO updates(long userId, UserDTO userDTO) {
		if (userDTO.uid != userId) // TODO throw meaningful exception
			return null;
		User user = User.findByUserId(userId);
		// user.userSIGN = "test";
		MapperSingelton.getInstance().map(userDTO, user);
		user.save();
		System.out.println("user.userSIGN: " + user.userSIGN);
		return user.getUserDTO();
	}

	/* (non-Javadoc)
	 * @see rest.interfaces.UserAPI#getUsersCampaigns(java.lang.String)
	 */
	@Override
	public CampaignListDTO getUsersCampaigns(long userId) {
		List<Campaign> campaigns = Campaign.findByUserId(userId);
		List<CampaignDTO> dtos = new ArrayList<CampaignDTO>();
		for(Campaign campaign : campaigns)
			dtos.add(MapperSingelton.getInstance().map(campaign, CampaignDTO.class));
		return new CampaignListDTO(dtos);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see rest.interfaces.UserAPI#getData()
	 */
	@Override
	public List<Data> getData() {
		List<Data> dataList = new ArrayList<Data>();
		for (int i = 0; i < 7; i++) {
			Data data = new Data();
			data.setName("user" + i);
			dataList.add(data);
		}
		return dataList;
	}

	/* (non-Javadoc)
	 * @see rest.interfaces.UserAPI#sendData(java.util.List)
	 */
	@Override
	public void sendData(List<Data> datas) {
		for (Data data : datas) {
			System.out.println(data.getName());
		}
	}

}
