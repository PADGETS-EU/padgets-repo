/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.converter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 * 
 * Object which will be returned to show if a operation succeeded. 
 */
@XmlRootElement
public class Success {
    
    private String success;
    private String message;

    public Success() {
    }

    public Success(String success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
    
    
}
