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
@XmlRootElement(name = "channels")
@XmlAccessorType(XmlAccessType.FIELD)
public class PublishChannelListDTO {

	public int count;
	@XmlElement(name = "channel")
	public List<PublishChannelDTO> channels;

	public PublishChannelListDTO(List<PublishChannelDTO> channels) {
		this.channels = channels;
		this.count = channels.size();
	}
}
