/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Campaign;
import de.fhg.fokus.persistence.Userdata;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hgo
 */
@Stateless
public class CampaignFacade extends AbstractFacade<Campaign> {
    @PersistenceContext(unitName = "de.fhg.fokus_PadgetsREST-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampaignFacade() {
        super(Campaign.class);
    }
    
            @Override
    public void create(Campaign campaign) {
        campaign.setIdCampaign(-1);
        getEntityManager().persist(campaign);
        getEntityManager().flush();
    }
}
