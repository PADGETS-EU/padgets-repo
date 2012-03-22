/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Comment;
import de.fhg.fokus.persistence.Message;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hannes Gorges
 */
@Stateless
public class CommentFacade extends AbstractFacade<Comment> {
    @PersistenceContext(unitName = "de.fhg.fokus_PadgetsREST-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentFacade() {
        super(Comment.class);
    }
    
        public List<Comment> getComments(Integer messageId, Integer from) {
        String query = "select * from Comment where idMessage = " + messageId
               + " ORDER BY createTime DESC"
               + " LIMIT " + from + ",50;";
        return this.executeNativeQueryForSelect(query);
    }
    
        public Long countComments(Integer messageId) {
        String query = "select count(*) from Comment where idMessage = " + messageId + ";";
                Query q = getEntityManager().createNativeQuery(query);
        return (Long) q.getResultList().get(0);
    }
    
}
