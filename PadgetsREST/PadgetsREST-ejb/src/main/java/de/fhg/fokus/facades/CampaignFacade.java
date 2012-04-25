/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Campaign;
import de.fhg.fokus.persistence.Publishchannel;
import de.fhg.fokus.persistence.Userdata;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hannes Gorges
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

    public void refresh(Campaign campaign) {
        getEntityManager().refresh(campaign);
    }
    
    public List<Campaign> getCampaignsForUser(Userdata ud) {
        String jpql = "SELECT c FROM Campaign c, IN (c.userroleList) ur"
                + " WHERE ur.idUserData = :idUser";  
      List<Campaign> cList = (List<Campaign>) getEntityManager().createQuery(jpql).setParameter("idUser", ud).getResultList();
      
      for (Campaign c : cList){
          for (Publishchannel pc : c.getPublishchannelList()){
              pc.setCount(0); //TODO echter counter setzen
          }
      }
      
      return cList;
    }

    
}
