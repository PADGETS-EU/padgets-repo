package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {

	public long ufid;

	public long publishedItemId;
	public long messageId;

	public long timestamp;
	public String content;
	public String userProfileUrl;
	public String network;
	public String networkCommentId;
	public String networkCommentUrl;


	public static Comment findByNetworkNameAndNetworkCommentId(String network, String commentId) {
		return Comment.find("byNetworkAndNetworkcommentid", network, commentId).first();
	}


	public static List<Comment> findByMessageId(long messageId) {
		return Comment.find("messageId", messageId).fetch();
	}

}
