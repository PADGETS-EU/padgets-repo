package rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "survey")
@XmlAccessorType(XmlAccessType.FIELD)
public class SurveyDTO {
	public long sid;

	public long campaignId;
	public String sKey;
	
	public String editUrl;
	public String voteUrl;
	public String resultsUrl;

}
