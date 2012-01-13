/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.converter;

import de.fhg.fokus.persistence.Answerer;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 * 
 * Container for the results.
 */

@XmlRootElement
public class Results {
    
  private List<Answerer>  voters = new ArrayList<Answerer>();

    public Results() {
    }   

    @XmlElement (name = "Voter")
    public List<Answerer> getVoters() {
        return voters;
    }

    public void setVoters(List<Answerer> voters) {
        this.voters = voters;
    }
    
  
}
