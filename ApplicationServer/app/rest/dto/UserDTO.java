package rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "padgetsuser")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO {
	public long uid;

	public String username;
	// OpenID
	public String openid;

	public String firstname;
	public String surname;
	public String middlename;
	public String email;

	public String gender;
	public String age;

	public List<String> roles;
	public String organization;
}
