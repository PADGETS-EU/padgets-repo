package rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comment")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentDTO {
	
	public long cid;
	
	public long messageId;
	
	public long timestamp;
	public String content;
	public String userProfileUrl;
	public String network;
	public String networkCommentId;
	public String networkCommentUrl;

}
