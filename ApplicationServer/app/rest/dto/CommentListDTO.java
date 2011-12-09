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
@XmlRootElement(name = "comments")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentListDTO {

	public int count;
	@XmlElement(name = "comment")
	public List<CommentDTO> comments;

	public CommentListDTO(List<CommentDTO> comments) {
		this.comments = comments;
		this.count = comments.size();
	}
}
