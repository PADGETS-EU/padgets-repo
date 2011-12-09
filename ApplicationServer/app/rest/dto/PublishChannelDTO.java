package rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
@XmlRootElement(name = "administratedpage")
@XmlAccessorType(XmlAccessType.FIELD)
public class PublishChannelDTO {
	public long pchid;
	public long accountId;
	// public long campaignId;
	public long userId;
	public String name;
	public String network;
	public String networkPageId;

	public boolean equals(PublishChannelDTO dto) {
		return this.pchid == dto.pchid;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof PublishChannelDTO)
			return this.equals((PublishChannelDTO) o);
		else
			return super.equals(o);
	}
}
