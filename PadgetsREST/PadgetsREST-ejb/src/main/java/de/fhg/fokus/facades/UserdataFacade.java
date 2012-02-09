/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Userdata;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hannes Gorges
 */
@Stateless
public class UserdataFacade extends AbstractFacade<Userdata> {
    @PersistenceContext(unitName = "de.fhg.fokus_PadgetsREST-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserdataFacade() {
        super(Userdata.class);
    }
    
    @Override
    public void create(Userdata u) {
        u.setIdUserData(-1);
        getEntityManager().persist(u);
        getEntityManager().flush();
    }
    
}
