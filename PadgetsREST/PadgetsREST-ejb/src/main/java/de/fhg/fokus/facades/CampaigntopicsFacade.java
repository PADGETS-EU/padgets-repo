/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Campaigntopics;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hgo
 */
@Stateless
public class CampaigntopicsFacade extends AbstractFacade<Campaigntopics> {
    @PersistenceContext(unitName = "de.fhg.fokus_PadgetsREST-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampaigntopicsFacade() {
        super(Campaigntopics.class);
    }
    
                @Override
    public void create(Campaigntopics campaign) {
        campaign.setIdCampaignTopics(-1);
        getEntityManager().persist(campaign);
        getEntityManager().flush();
    }
}
