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
@XmlRootElement(name = "padgetsmedias")
@XmlAccessorType(XmlAccessType.FIELD)
public class MediaListDTO {

	public int count;
	@XmlElement(name = "padgetsmedia")
	public List<MediaDTO> medias;

	public MediaListDTO(List<MediaDTO> medias) {
		this.medias = medias;
		this.count = medias.size();
	}
}
