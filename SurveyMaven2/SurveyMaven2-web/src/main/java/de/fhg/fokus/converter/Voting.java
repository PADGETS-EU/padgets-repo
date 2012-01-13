/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.converter;

import de.fhg.fokus.persistence.Answerer;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author hgo
 * 
 * Container for adding votes.
 */

@XmlRootElement
public class Voting {
    
    private Answerer voter;
    private List<String> answers;

    public Voting() {
    }

    @XmlElement (name = "Answer")
    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Answerer getVoter() {
        return voter;
    }

    public void setVoter(Answerer voter) {
        this.voter = voter;
    }
    
    
}
