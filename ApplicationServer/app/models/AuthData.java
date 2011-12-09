package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class AuthData extends Model {

	public long userId;
	public String network;
	public String redirectUrl;

	public String type;
	public long accountId;
	public String hash;
	public String permissions;
	public String expire;

	public AuthData(long uid, String network, String redirectUrl, String permissions) {
		this.network = network;
		this.userId = uid;
		this.redirectUrl = redirectUrl;
		this.permissions = permissions;
		expire = (new Date()).getTime() + 200000 + "";
		hash = makeAuthDataHash();
	}

	public AuthData(String authType, OpenIDData data) {
		this.type = authType;
		this.network = data.provider;
		this.redirectUrl = data.redirectUrl;
		expire = (new Date()).getTime() + 2000000 + "";
		hash = makeAuthDataHash();
	}

	private String makeAuthDataHash() {
		return new Crypto().sign(network + userId + accountId + expire);
	}
}
