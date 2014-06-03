/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ztintor.ejb.sb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.ztintor.ejb.eb.States;

/**
 *
 * @author zoran
 */
@Stateless
public class StatesFacade extends AbstractFacade<States> {
    @PersistenceContext(unitName = "ztintor_zadaca_4_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatesFacade() {
        super(States.class);
    }
    
        public List<States> filtrirajDrzave(String naziv) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<States> drzave = cq.from(States.class);
        cq.select(drzave);
        cq.where(cb.like(drzave.<String>get("name"), naziv + "%"));
                 
        return em.createQuery(cq).getResultList();
    }
}
