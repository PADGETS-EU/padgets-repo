/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Publisheditem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hannes Gorges
 */
@Stateless
public class PublisheditemFacade extends AbstractFacade<Publisheditem> {
    @PersistenceContext(unitName = "de.fhg.fokus_PadgetsREST-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublisheditemFacade() {
        super(Publisheditem.class);
    }
    
                    @Override
    public void create(Publisheditem p) {
        p.setIdPublishedItem(-1);
        getEntityManager().persist(p);
        getEntityManager().flush();
    }
}
