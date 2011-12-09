package rest.dto;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import models.Comment;

/**
 * 
 * @author Lukasz Radziwonowicz
 * 
 */

@XmlRootElement(name = "padgetsmessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class MediaDTO {
	public long medid;

	// public Sender sender;

	public String campaignRef;
	public String campaignTitle;
	public String type;
	public String url;
	public String title;
	// public TargetSocialMediaPlatforms targetsmp;

	public boolean isPublished;
	public String timestamp;
	public String permalink;
	
	//=============================
	public Set<CommentDTO> comments;
}
