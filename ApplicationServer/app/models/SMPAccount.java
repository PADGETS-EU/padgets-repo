package models;

import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class SMPAccount extends Model {

	public long accountId;
	public long userId;
	public String networkUserId;
	public String network;
	public String username;
	public String name;
	public String profileUrl;

	// =======================
	public String oAuthToken;
	public String oAuthSecret;
	public String oAuth2Token;

	/**
	 * @param accountId
	 * @return
	 */
	public static SMPAccount findByAccountId(long accountId) {
		return SMPAccount.find("accountId", accountId).first();
	}

	/**
	 * @param userId2
	 * @return
	 */
	public static List<SMPAccount> findByUserId(long userId) {
		return SMPAccount.find("userId", userId).fetch();
	}

	/**
	 * @param userId2
	 * @param valueOf
	 * @return
	 */
	public static SMPAccount findByUserAndNetworkId(long userId, String networkUserId) {
		return SMPAccount.find("byUseridAndNetworkuserid", userId, networkUserId).first();
	}
}
