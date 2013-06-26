/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;

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
      getPublishchannels(cList);
      
      return cList;
    }

    public List<Campaign> searchCampaigns(String keyword, String location, Integer offset) {
      CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
      CriteriaQuery<Campaign> cq = cb.createQuery(Campaign.class);
      Root<Campaign> campaignRoot = cq.from(Campaign.class);
            
      cq.select(campaignRoot);
      List<Predicate> criteria = new ArrayList<Predicate>();
      if(keyword != null && !keyword.isEmpty()){          
          criteria.add(cb.like(campaignRoot.get(Campaign_.title), "%"+keyword+"%"));
          criteria.add(cb.like(campaignRoot.get(Campaign_.hashTag), "%"+keyword+"%"));
          criteria.add(cb.like(campaignRoot.get(Campaign_.notes), "%"+keyword+"%"));                                       
      }
      Predicate orP = cb.or(criteria.toArray(new Predicate[0]));
      Predicate notLikeP = cb.notLike(campaignRoot.get(Campaign_.hashTag), "%InitialCampaign%");          
      
      if(location != null && !location.isEmpty()){
          Join<Campaign, Location> locationJoin = campaignRoot.join("idLocation");
          cq.where(cb.and(orP, notLikeP, cb.like(locationJoin.get(Location_.name), "%"+location+"%")));
      }
      cq.where(cb.and(orP, notLikeP));
      TypedQuery<Campaign> q = em.createQuery(cq).setFirstResult(offset).setMaxResults(200);
      List<Campaign> cList = q.getResultList();
      return cList;
    }
    
    private void getPublishchannels(List<Campaign> cList) {
        for (Campaign c : cList){
            for (Publishchannel pc : c.getPublishchannelList()){
                pc.setCount(0); //TODO echter counter setzen
            }
        }
    }    
    
    
}
