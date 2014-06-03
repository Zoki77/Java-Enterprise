/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ztintor.ejb.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.foi.nwtis.ztintor.ejb.eb.Polaznici;

/**
 *
 * @author zoran
 */
@Stateless
public class PolazniciFacade extends AbstractFacade<Polaznici> {
    @PersistenceContext(unitName = "ztintor_zadaca_4_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PolazniciFacade() {
        super(Polaznici.class);
    }
    
}
