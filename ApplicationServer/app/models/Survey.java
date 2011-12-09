package models;

import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;
import rest.dto.SurveyDTO;

@Entity
public class Survey extends Model {

	public long sid;

	public long campaignId;
	public String sKey;
	
	public String editUrl;
	public String voteUrl;
	public String resultsUrl;
	
	public static SurveyDTO findBySurveyId(long surveyId) {
		return Survey.find("sid", surveyId).first();
	}
	public static List<Survey> findByCampaignId(long campaignId) {
		return Survey.find("campaignId", campaignId).fetch();
	}


}
