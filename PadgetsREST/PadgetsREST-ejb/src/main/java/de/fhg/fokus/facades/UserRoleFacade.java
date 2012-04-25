/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Campaign;
import de.fhg.fokus.persistence.Userdata;
import de.fhg.fokus.persistence.Userrole;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HAnnes Gorges
 */
@Stateless
public class UserRoleFacade extends AbstractFacade<Userrole> {

    @PersistenceContext(unitName = "de.fhg.fokus_PadgetsREST-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Userrole u) {
        u.setIdUserRole(-1);
        getEntityManager().persist(u);
        getEntityManager().flush();
    }

    /**
     * Returns true if the user is manager of the campaign.
     * @param ud
     * @param c
     * @return 
     */
    public boolean isCampaignManager(Userdata ud, Campaign c) {
        Userrole ur = getUserRole(ud, c);
        if (ur == null || ur.getUserRole().equals("helper")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns true if the user is manager or helper of the campaign.
     * @param ud
     * @param c
     * @return 
     */
    public boolean isCampaignPaticipant(Userdata ud, Campaign c) {
        Userrole ur = getUserRole(ud, c);
        if (ur == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public Userrole getUserRole(Userdata ud, Campaign c) {
        String jpql = "SELECT u FROM Userrole u WHERE u.idUserData = :idUserData AND u.idCampaign = :idCampaign";
        return (Userrole) getEntityManager().createQuery(jpql).setParameter("idUserData", ud).setParameter("idCampaign", c).getSingleResult();
    }

    public UserRoleFacade() {
        super(Userrole.class);
    }
}