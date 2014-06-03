/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ztintor.ejb.sb;

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.ztintor.ejb.eb.Cities;

/**
 *
 * @author zoran
 */
@Stateless
public class CitiesFacade extends AbstractFacade<Cities> {
    @PersistenceContext(unitName = "ztintor_zadaca_4_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitiesFacade() {
        super(Cities.class);
    }
    
    /**
     * Funkcija za filtriranje gradova. Funkcija prima Set koji sadrži stringove
     * država. Pomoću criteria buildera pretražuju se gradovi 
     * pomoću klase Cities. Oni koji prolaze uvjet spremaju se u listu koju
     * funkcija vraća.
     * @param drzava
     * @return 
     */
        public List<Cities> filtrirajGradove(Set<String> drzava) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Cities> gradovi = cq.from(Cities.class);
        cq.select(gradovi);
        cq.where(gradovi.<String>get("citiesPK").<String>get("state").in(drzava));
                 
        return em.createQuery(cq).getResultList();
    }
    
        /**
         * Ista funkcija kao prethodna uz dodatno filtriranje prema prosljeđenom
         * Stringu.
         * @param drzava
         * @param naziv
         * @return 
         */
     public List<Cities> filtrirajGradove(Set<String> drzava, String naziv) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Cities> gradovi = cq.from(Cities.class);
        cq.select(gradovi);
        cq.where(cb.and(gradovi.<String>get("citiesPK").<String>get("state").in(drzava),
                cb.like(gradovi.<String>get("name"), naziv + "%")));
                 
        return em.createQuery(cq).getResultList();
    }

}


