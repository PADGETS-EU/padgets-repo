package rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
@XmlRootElement(name = "padgetscampaigns")
@XmlAccessorType(XmlAccessType.FIELD)
public class CampaignListDTO {

	public int count;
	@XmlElement(name = "padgetscampaign")
	public List<CampaignDTO> campaigns;

	public CampaignListDTO(List<CampaignDTO> campaigns) {
		this.campaigns = campaigns;
		this.count = campaigns.size();
	}
}
