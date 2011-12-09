/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import play.Logger;
import play.db.jpa.Model;
import play.libs.OpenID;
import utils.Padgets;

/**
 * 
 * @author iptv
 */
@Entity
public class OpenIDProvider extends Model {

	public String name;
	public String discoveryUrl;
	@ElementCollection
	public Map<String, String> requiredParameters;

	public void authenticate(AuthData authData) {

		Logger.debug("Discovery URL: %s", this.discoveryUrl);
		Logger.debug("OpenID provider: %s", this.name);

		OpenID oid = OpenID.id(this.discoveryUrl);
		oid.required("fullname", "http://axschema.org/namePerson");
		oid.required("lastname", "http://axschema.org/namePerson/last");
		oid.required("firstname", "http://axschema.org/namePerson/first");
		oid.required("email", "http://axschema.org/contact/email");
		oid.required("profileImage", "http://axschema.org/media/image/default");

		oid.returnTo(Padgets.url + "openID/" + authData.hash);
		oid.forRealm(Padgets.url);

		oid.verify(); // will redirect the user
	}

	public static OpenIDProvider findByName(String network) {
		OpenIDProvider provider = OpenIDProvider.find("name", network).first();
		return provider;
	}
}
