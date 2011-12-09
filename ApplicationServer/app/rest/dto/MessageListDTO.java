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
@XmlRootElement(name = "padgetsmessages")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageListDTO {

	public int count;
	@XmlElement(name = "padgetsmessage")
	public List<MessageDTO> messages;

	public MessageListDTO(List<MessageDTO> messages) {
		this.messages = messages;
		this.count = messages.size();
	}
}
