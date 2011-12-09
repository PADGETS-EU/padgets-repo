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
@XmlRootElement(name = "surveys")
@XmlAccessorType(XmlAccessType.FIELD)
public class SurveyListDTO {

	public int count;
	@XmlElement(name = "survey")
	public List<SurveyDTO> surveys;

	public SurveyListDTO(List<SurveyDTO> surveys) {
		this.surveys = surveys;
		this.count = surveys.size();
	}
}
