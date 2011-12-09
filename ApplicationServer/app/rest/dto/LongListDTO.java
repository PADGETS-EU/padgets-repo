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
@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.FIELD)
public class LongListDTO {

	public int count;
	@XmlElement(name = "item")
	public List<Long> list;

	public LongListDTO(List<Long> list) {
		this.list = list;
		this.count = list.size();
	}
}
