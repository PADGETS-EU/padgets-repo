/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.rest.converter;

import de.fhg.fokus.persistence.Answerer;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */

@XmlRootElement
public class Results {
    
  private List<Answerer>  votes = new ArrayList<Answerer>();

    public Results() {
    }

    @XmlElement (name = "Voter")
    public List<Answerer> getVotes() {
        return votes;
    }

    public void setVotes(List<Answerer> votes) {
        this.votes = votes;
    }
    
  
}
