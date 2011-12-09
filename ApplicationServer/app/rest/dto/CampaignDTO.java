/**
 * 
 */
package rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
@XmlRootElement(name = "padgetscampaign")
@XmlAccessorType(XmlAccessType.FIELD)
public class CampaignDTO {

	public long cid;
	public String pmakerref;
	public String title; 
	public String notes;

	public String creationdate;
	public String startdate;
	public String enddate;

	public List<String> topics;
	public String url;

	public String language;

	public boolean active;

	public List<String> assConsRef;
}
