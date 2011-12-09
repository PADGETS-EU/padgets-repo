/**
 * 
 */
package rest.impl;

import java.util.ArrayList;
import java.util.List;

import components.Publisher;

import models.Campaign;
import models.Comment;
import models.Message;
import rest.conf.MapperSingelton;
import rest.dto.CommentDTO;
import rest.dto.CommentListDTO;
import rest.dto.MessageDTO;
import rest.interfaces.MessageAPI;
import utils.Padgets;

/**
 * @author Lukasz Radziwonowicz
 *
 */
public class MessageImpl implements MessageAPI {

	/* (non-Javadoc)
	 * @see rest.interfaces.MessageAPI#create(long)
	 */
	@Override
	public MessageDTO create(long campaignId) {
		Campaign campaign = Campaign.findByCampaignId(campaignId);
		if(campaign == null) //campaign does not exists; 
			return null; // TODO proper error handling
		
		Message message = new Message();
		message.mid = Padgets.generateNewId();
		message.campaignId = campaign.cid;
		message.timestamp = System.currentTimeMillis();
		message.save();
		
		return message.getDTO();
	}

	/* (non-Javadoc)
	 * @see rest.interfaces.MessageAPI#create(long, rest.dto.MessageDTO)
	 */
	@Override
	public MessageDTO create(long campaignId, MessageDTO messageDTO) {
		Campaign campaign = Campaign.findByCampaignId(campaignId);
		if(campaign == null) //campaign does not exists; 
			return null; // TODO proper error handling
		
		Message message = new Message();
		message.mid = Padgets.generateNewId();
		message.campaignId = campaign.cid;
		message.timestamp = System.currentTimeMillis();
		message.save();
		messageDTO.msgid = message.mid;
		MapperSingelton.getInstance().map(messageDTO, message);
		message.save();
		
		Publisher.publish(campaign, message);
				
		return message.getDTO();
	}	
	/* (non-Javadoc)
	 * @see rest.interfaces.MessageAPI#get(long)
	 */
	@Override
	public MessageDTO get(long messageId) {
		Message message = Message.findByMessageId(messageId);
		return message.getDTO();
	}

	/* (non-Javadoc)
	 * @see rest.interfaces.MessageAPI#updates(long, rest.dto.MessageDTO)
	 */
	@Override
	public MessageDTO updates(long messageId, MessageDTO messageDTO) {
		if(messageDTO.msgid != messageId)
			return null; // TODO proper error handling
		Message message = Message.findByMessageId(messageId);
		MapperSingelton.getInstance().map(messageDTO, message);
		message.save();
		
		Campaign campaign = Campaign.findByCampaignId(message.campaignId);
		Publisher.update(campaign, message);
		
		return message.getDTO();
	}

	/* (non-Javadoc)
	 * @see rest.interfaces.MessageAPI#delete(long)
	 */
	@Override
	public void delete(long messageId) {
		// TODO check campaign status, active/not active, how we can delete
		// messages already posted to SMPs
		Message message = Message.findByMessageId(messageId);
		message.delete();
		// TODO return proper message: success/error
	}

	@Override
	public CommentListDTO getComments(long messageId) {
		List<Comment> comments = Message.getComments(messageId);
		List<CommentDTO> dtos = new ArrayList<CommentDTO>();
		for (Comment comment : comments) {
			dtos.add(MapperSingelton.getInstance().map(comment, CommentDTO.class));
		}
		return new CommentListDTO(dtos);
	}

	
}
