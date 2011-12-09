/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.models;

/**
 *
 * @author hgo
 */
public class CollapseModel {
    
    Boolean collapse;

    public CollapseModel() {
        this.collapse = false;
    }

    public Boolean getCollapse() {
        return collapse;
    }

    public void setCollapse(Boolean collapse) {
        this.collapse = collapse;
    }
    
}
