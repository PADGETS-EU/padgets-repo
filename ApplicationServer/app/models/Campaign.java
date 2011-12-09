package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import play.db.jpa.Model;
import rest.conf.MapperSingelton;
import rest.dto.CampaignDTO;

@Entity
public class Campaign extends Model {

	public Long cid;
	public long userId;
	public String title;
	public String notes;

	public String creationdate;
	public String startdate;
	public String enddate;

	@ElementCollection
	public List<String> topics;
	public String url;

	public String language;

	public boolean active;

	@ElementCollection
	public List<String> assConsRef;

	// private TargetCitizens targetcitizens;
	//
	// private AnalyticsOutput analytics;

	// =========================================

	@ElementCollection
	public Set<Long> channels = new TreeSet<Long>();
	/**
	 * @param campaignId
	 * @return
	 */
	public static Campaign findByCampaignId(long campaignId) {
		return Campaign.find("cid", campaignId).first();
	}

	/**
	 * @return
	 */
	public CampaignDTO getDTO() {
		return MapperSingelton.getInstance().map(this, CampaignDTO.class);
	}

	/**
	 * @param userId2
	 * @return
	 */
	public static List<Campaign> findByUserId(long userId) {
		return Campaign.find("userId", userId).fetch();
	}
}
