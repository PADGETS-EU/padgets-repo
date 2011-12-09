package models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import play.db.jpa.Model;
import play.libs.Codec;
import rest.conf.MapperSingelton;
import rest.dto.UserDTO;

@Entity
public class User extends Model {
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

	@ElementCollection
	public Set<String> roles;
	public String organization;

	// ===========================
	// TODO set up security
	public String userSIGN;
	public String openIDVerifiedIdentifier;
	public long authtime;

	public static User findByOpenIDVerifiedIdentifier(String id) {
		return User.find("openIDVerifiedIdentifier", id).first();
	}

	public String generateUserSIGN() {
		return Codec.hexMD5(this.openid + "oneopenapi" + this.authtime);
	}

	public long generateAuthtime() {
		return new Date().getTime();
	}

	public static User findByUserId(long userId) {
		return User.find("uid", userId).first();
	}

	/**
	 * @return
	 */
	public UserDTO getUserDTO() {
		return MapperSingelton.getInstance().map(this, UserDTO.class);		
	}
}
