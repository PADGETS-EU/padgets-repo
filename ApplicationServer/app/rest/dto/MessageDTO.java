package rest.dto;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
public class MessageDTO {
	public long msgid;

	// public Sender sender;

	public String title;
	public String campaignRef;
	public String campaignTitle;
	public String content;
	public List<String> links;

	public String geolocation;

	// public TargetSocialMediaPlatforms targetsmp;
	
	public boolean isPublished;
	public String timestamp;
	public String permalink;
	
	//========================
	public Set<CommentDTO> comments = new TreeSet<CommentDTO>();
}
