package models;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import play.db.jpa.Model;

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

	/**
	 * @param uid
	 * @return
	 */
	public static User findByUserId(long userId) {
		return User.find("uid", userId).first();
	}
}
