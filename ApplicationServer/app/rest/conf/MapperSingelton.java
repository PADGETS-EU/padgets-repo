/**
 * 
 */
package rest.conf;

import models.Campaign;
import models.Comment;
import models.Media;
import models.Message;
import models.PublishChannel;
import models.SMPAccount;
import models.Survey;
import models.User;

import org.dozer.DozerBeanMapper;
import org.dozer.classmap.RelationshipType;
import org.dozer.loader.api.BeanMappingBuilder;

import rest.dto.CampaignDTO;
import rest.dto.CommentDTO;
import rest.dto.MediaDTO;
import rest.dto.MessageDTO;
import rest.dto.PublishChannelDTO;
import rest.dto.SMPAccountDTO;
import rest.dto.SurveyDTO;
import rest.dto.UserDTO;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
public class MapperSingelton {

	static DozerBeanMapper mapper = null;

	public static DozerBeanMapper getInstance() {
		if (mapper == null) {
			mapper = init();
		}
		return mapper;

	}

	public static DozerBeanMapper init() {
		mapper = new DozerBeanMapper();

		BeanMappingBuilder builder = new BeanMappingBuilder() {
			protected void configure() {
				mapping(User.class, UserDTO.class).fields("roles", "roles",
						collectionStrategy(true, RelationshipType.CUMULATIVE));
				mapping(SMPAccountDTO.class, SMPAccount.class);
				mapping(PublishChannelDTO.class, PublishChannel.class);
				mapping(Campaign.class, CampaignDTO.class)
						.fields(field("userId").accessible(true),
								field("pmakerref").accessible(true))
						.fields("topics", "topics",
								collectionStrategy(true, RelationshipType.CUMULATIVE))
						.fields("assConsRef", "assConsRef",
								collectionStrategy(true, RelationshipType.CUMULATIVE));
				mapping(MessageDTO.class, Message.class)
						.fields(field("campaignRef").accessible(true),
								field("campaignId").accessible(true))
						.fields(field("msgid").accessible(true), field("mid").accessible(true))
						.fields("links", "links",
								collectionStrategy(true, RelationshipType.CUMULATIVE))
						.fields("comments", "comments",
								collectionStrategy(true, RelationshipType.CUMULATIVE));
				mapping(MediaDTO.class, Media.class)
						.fields(field("campaignRef").accessible(true),
								field("campaignId").accessible(true))
						.fields(field("medid").accessible(true), field("mid").accessible(true))
						.fields("comments", "comments",
								collectionStrategy(true, RelationshipType.CUMULATIVE));
				mapping(CommentDTO.class, Comment.class);
				mapping(SurveyDTO.class, Survey.class);
			}
		};

		mapper.addMapping(builder);
		return mapper;
	}
}
