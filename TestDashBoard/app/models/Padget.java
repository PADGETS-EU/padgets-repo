package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Padget extends Model {
	public String padgetID;
	public String owner;
	public boolean published;
	@ElementCollection
	List<String> message=new ArrayList<String>();
	@ElementCollection
	List<String> media=new ArrayList<String>();
	
}
