/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.misc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */

@XmlRootElement
public class Counter {
    
    private long count;

    public long getCount() {
        return count;
    }

    @XmlElement
    public void setCount(long count) {
        this.count = count;
    }   
    
}
