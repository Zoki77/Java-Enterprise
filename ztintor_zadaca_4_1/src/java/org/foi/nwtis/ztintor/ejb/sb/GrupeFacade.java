/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ztintor.ejb.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.foi.nwtis.ztintor.ejb.eb.Grupe;

/**
 *
 * @author zoran
 */
@Stateless
public class GrupeFacade extends AbstractFacade<Grupe> {
    @PersistenceContext(unitName = "ztintor_zadaca_4_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupeFacade() {
        super(Grupe.class);
    }
    
}
