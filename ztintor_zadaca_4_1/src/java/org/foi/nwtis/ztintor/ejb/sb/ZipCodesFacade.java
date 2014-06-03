/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ztintor.ejb.sb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.ztintor.ejb.eb.ZipCodes;

/**Generirana klasa na temelju odabranih podataka iz baze za zip kodove.
 *
 * @author zoran
 */
@Stateless
public class ZipCodesFacade extends AbstractFacade<ZipCodes> {

    @PersistenceContext(unitName = "ztintor_zadaca_4_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZipCodesFacade() {
        super(ZipCodes.class);
    }

    /**
     * Funkcija za filtriranje zip kodova. Funkcija prima Set koji sadrži stringove
     * oblika {state - county - city}. Pomoću criteria buildera pretražuju se zipovi 
     * pomoću klase ZipCodes te se države, okruzi i gradovi spajaju u odgovarajući
     * string kako bi se provjerilo da li se on nalazi u setu. Ako se nalazi selektira se
     * i vraća u listi.
     * @param set
     * @return 
     */
    
    public List<ZipCodes> filtrirajZipKodove(Set<String> set) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<ZipCodes> zipovi = cq.from(ZipCodes.class);
        cq.select(zipovi);
        cq.where(cb.concat(cb.concat(cb.concat(zipovi.<String>get("cities").<String>get("citiesPK").<String>get("state"), " - "),
                                     cb.concat(zipovi.<String>get("cities").<String>get("citiesPK").<String>get("county"), " - ")),
                           zipovi.<String>get("cities").<String>get("citiesPK").<String>get("city")).in(set)); 

        return em.createQuery(cq).getResultList();
    }
    

}
