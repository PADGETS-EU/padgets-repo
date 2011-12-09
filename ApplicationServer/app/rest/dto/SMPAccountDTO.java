package rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "smpAccount")
@XmlAccessorType(XmlAccessType.FIELD)
public class SMPAccountDTO {

	public long accountId;
	public long userId;
	public String networkUserId;
	public String network;
	public String username;
	public String name;
	public String profileUrl;
}
