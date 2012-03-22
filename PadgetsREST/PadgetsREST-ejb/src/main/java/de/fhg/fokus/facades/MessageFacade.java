/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Campaign;
import de.fhg.fokus.persistence.Message;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;

/**
 *
 * @author Hannes Gorges
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "de.fhg.fokus_PadgetsREST-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }

    @Override
    public void create(Message message) {
        message.setIdMessage(-1);
        getEntityManager().persist(message);
        getEntityManager().flush();

    }

    public void refresh(Message u) {
        getEntityManager().refresh(u);
    }

    public List<Message> getMessages(Integer campaignId, Integer from) {
        String query = "select * from Message where idCampaign = " + campaignId
               + " ORDER BY createTime DESC"
               + " LIMIT " + from + ",50;";
        return this.executeNativeQueryForSelect(query);
    }
    
        public Long countMessages(Integer campaignId) {
        String query = "select count(*) from Message where idCampaign = " + campaignId + ";";
                Query q = getEntityManager().createNativeQuery(query);
        return (Long) q.getResultList().get(0);
    }
}
