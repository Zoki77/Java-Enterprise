/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ztintor.web.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import net.wxbug.api.LiveWeatherData;
import org.foi.nwtis.ztintor.ejb.eb.Cities;
import org.foi.nwtis.ztintor.ejb.eb.States;
import org.foi.nwtis.ztintor.ejb.eb.ZipCodes;
import org.foi.nwtis.ztintor.ejb.sb.CitiesFacade;
import org.foi.nwtis.ztintor.ejb.sb.StatesFacade;
import org.foi.nwtis.ztintor.ejb.sb.WeatherBugKlijent;
import org.foi.nwtis.ztintor.ejb.sb.ZipCodesFacade;
import org.foi.nwtis.ztintor.web.kontrole.MeteoIspis;

/**
 * Klasa koja sadrži sve ključne funkcije za komunikaciju s pogledom.
 *
 * @author zoran
 */
@Named(value = "odabiZipKodovaZaGradove")
@SessionScoped
public class OdabiZipKodovaZaGradove implements Serializable {

    @EJB
    private WeatherBugKlijent weatherBugKlijent;
    @EJB
    private ZipCodesFacade zipCodesFacade;
    @EJB
    private StatesFacade statesFacade;
    @EJB
    private CitiesFacade citiesFacade;
    private String filterDrzava;
    private TreeMap<String, Object> popisDrzava;
    private List<String> popisDrzavaOdabrano;
    private TreeMap<String, Object> popisOdabranihDrzava;
    private List<String> popisOdabranihDrzavaOdabrano;
    private String filterGradova;
    private TreeMap<String, Object> popisGradova;
    private List<String> popisGradovaOdabrano;
    private TreeMap<String, Object> popisOdabranihGradova;
    private List<String> popisOdabranihGradovaOdabrano;
    private String filterZipKodova;
    private TreeMap<String, Object> popisZipKodova;
    private List<String> popisZipKodovaOdabrano;
    private TreeMap<String, Object> popisOdabranihZipKodova;
    private List<String> popisOdabranihZipKodovaOdabrano;
    private LiveWeatherData meteoWSPodatak;
    private List<MeteoIspis> meteoIspis = new ArrayList<MeteoIspis>();

    /**
     * Creates a new instance of OdabiZipKodovaZaGradove
     */
    public OdabiZipKodovaZaGradove() {
    }

    public String getFilterDrzava() {
        return filterDrzava;
    }

    public void setFilterDrzava(String filterDrzava) {
        this.filterDrzava = filterDrzava;
    }

    /**
     * funkcija koja dohvaća popis država
     *
     * @return
     */
    public Map<String, Object> getPopisDrzava() {
        popisDrzava = new TreeMap<String, Object>();
        List<States> drzave;
        if (filterDrzava == null || filterDrzava.trim().isEmpty()) {
            drzave = statesFacade.findAll();
        } else {
            drzave = statesFacade.filtrirajDrzave(filterDrzava.toUpperCase());
        }

        for (States drzava : drzave) {
            popisDrzava.put(drzava.getName(), drzava.getName());
        }
        return popisDrzava;
    }

    public void setPopisDrzava(TreeMap<String, Object> popisDrzava) {
        this.popisDrzava = popisDrzava;
    }

    public List<String> getPopisDrzavaOdabrano() {
        return popisDrzavaOdabrano;
    }

    public void setPopisDrzavaOdabrano(List<String> popisDrzavaOdabrano) {
        this.popisDrzavaOdabrano = popisDrzavaOdabrano;
    }

    public Map<String, Object> getPopisOdabranihDrzava() {
        return popisOdabranihDrzava;
    }

    public void setPopisOdabranihDrzava(TreeMap<String, Object> popisOdabranihDrzava) {
        this.popisOdabranihDrzava = popisOdabranihDrzava;
    }

    public List<String> getPopisOdabranihDrzavaOdabrano() {
        return popisOdabranihDrzavaOdabrano;
    }

    public void setPopisOdabranihDrzavaOdabrano(List<String> popisOdabranihDrzavaOdabrano) {
        this.popisOdabranihDrzavaOdabrano = popisOdabranihDrzavaOdabrano;
    }

    public String getFilterGradova() {
        return filterGradova;
    }

    public void setFilterGradova(String filterGradova) {
        this.filterGradova = filterGradova;
    }

    /**
     * funkcija koja dohvaća popis gradova
     *
     * @return
     */
    public Map<String, Object> getPopisGradova() {
        popisGradova = new TreeMap<String, Object>();

        if (popisOdabranihDrzava == null
                || popisOdabranihDrzava.isEmpty()) {
            return popisGradova;
        }
        List<Cities> gradovi;
        if (filterGradova == null || filterGradova.isEmpty()) {
            gradovi = citiesFacade.filtrirajGradove(popisOdabranihDrzava.keySet());
        } else {
            gradovi = citiesFacade.filtrirajGradove(popisOdabranihDrzava.keySet(), filterGradova.toUpperCase());
        }


        for (Cities g : gradovi) {
            String grad = g.getCitiesPK().getState() + " - "
                    + g.getCitiesPK().getCounty() + " - "
                    + g.getCitiesPK().getCity();


            popisGradova.put(grad, grad);
        }

        return popisGradova;
    }

    public void setPopisGradova(TreeMap<String, Object> popisGradova) {
        this.popisGradova = popisGradova;
    }

    public List<String> getPopisGradovaOdabrano() {
        return popisGradovaOdabrano;
    }

    public void setPopisGradovaOdabrano(List<String> popisGradovaOdabrano) {
        this.popisGradovaOdabrano = popisGradovaOdabrano;
    }

    public Map<String, Object> getPopisOdabranihGradova() {
        return popisOdabranihGradova;
    }

    public void setPopisOdabranihGradova(TreeMap<String, Object> popisOdabranihGradova) {
        this.popisOdabranihGradova = popisOdabranihGradova;
    }

    public List<String> getPopisOdabranihGradovaOdabrano() {
        return popisOdabranihGradovaOdabrano;
    }

    public void setPopisOdabranihGradovaOdabrano(List<String> popisOdabranihGradovaOdabrano) {
        this.popisOdabranihGradovaOdabrano = popisOdabranihGradovaOdabrano;
    }

    public String getFilterZipKodova() {
        return filterZipKodova;
    }

    public void setFilterZipKodova(String filterZipKodova) {
        this.filterZipKodova = filterZipKodova;
    }

    /**
     * funkcija koja dohvaća popis zip kodova. U njoj se obavlja 
     * filtriranje prema potrebi.
     *
     * @return
     */
    public Map<String, Object> getPopisZipKodova() {
        popisZipKodova = new TreeMap<String, Object>();

        if (popisOdabranihGradova == null
                || popisOdabranihGradova.isEmpty()) {
            return popisZipKodova;
        }

        List<ZipCodes> zipovi;

        zipovi = zipCodesFacade.filtrirajZipKodove(popisOdabranihGradova.keySet());
        if (filterZipKodova == null || filterZipKodova.isEmpty()) {
            for (ZipCodes zk : zipovi) {
                String zipKod = zk.getZip().toString() + " - "
                        + zk.getCities().getCitiesPK().getState() + " - "
                        + zk.getCities().getCitiesPK().getCounty() + " - "
                        + zk.getCities().getCitiesPK().getCity();
                popisZipKodova.put(zipKod, zipKod);
            }

        } else {
            for (ZipCodes g : zipovi) {
                if (g.getZip().toString().startsWith(filterZipKodova)) {
                    String zipKod = g.getZip().toString() + " - "
                            + g.getCities().getCitiesPK().getState() + " - "
                            + g.getCities().getCitiesPK().getCounty() + " - "
                            + g.getCities().getCitiesPK().getCity();
                    popisZipKodova.put(zipKod, zipKod);
                }
            }
        }

        return popisZipKodova;
    }

    public void setPopisZipKodova(TreeMap<String, Object> popisZipKodova) {
        this.popisZipKodova = popisZipKodova;
    }

    public List<String> getPopisZipKodovaOdabrano() {
        return popisZipKodovaOdabrano;
    }

    public void setPopisZipKodovaOdabrano(List<String> popisZipKodovaOdabrano) {
        this.popisZipKodovaOdabrano = popisZipKodovaOdabrano;
    }

    public Map<String, Object> getPopisOdabranihZipKodova() {
        return popisOdabranihZipKodova;
    }

    public void setPopisOdabranihZipKodova(TreeMap<String, Object> popisOdabranihZipKodova) {
        this.popisOdabranihZipKodova = popisOdabranihZipKodova;
    }

    public List<String> getPopisOdabranihZipKodovaOdabrano() {
        return popisOdabranihZipKodovaOdabrano;
    }

    public void setPopisOdabranihZipKodovaOdabrano(List<String> popisOdabranihZipKodovaOdabrano) {
        this.popisOdabranihZipKodovaOdabrano = popisOdabranihZipKodovaOdabrano;
    }

    /**
     * funkcija koja dohvaća listu meteo podataka. Lista se puni objektima iz klase 
     * MeteoIspis u koje se stavlju zeljeni podaci za prikaz.
     *
     * @return
     */
    public List<MeteoIspis> getMeteoIspis() {

        if (popisOdabranihZipKodova == null || popisOdabranihZipKodova.isEmpty()) {
            meteoIspis = null;
        } else {
            meteoIspis = new ArrayList<MeteoIspis>();
            String zaSplit = popisOdabranihZipKodova.toString();
            System.out.println(zaSplit);
            String[] split = zaSplit.split(",|-|=");
            String zip;
            String state;
            String county;
            String city;
            for (int i = 0; i < split.length; i = i + 8) {
                zip = split[i].trim();
                state = split[i + 1].trim();
                county = split[i + 2].trim();
                city = split[i + 3].trim();
                if (i == 0) {
                    zip = zip.substring(1, zip.length());
                }
                System.out.println(zip + state + county + city);
                meteoWSPodatak = weatherBugKlijent.dajMeteoPodatke(zip);
                System.out.println(meteoWSPodatak.getZipCode());
                MeteoIspis met = new MeteoIspis(zip, state + " - " + county + " - " + city, meteoWSPodatak.getTemperature(),
                        meteoWSPodatak.getHumidity(), meteoWSPodatak.getLongitude(), meteoWSPodatak.getLatitude());

                meteoIspis.add(met);
            }
        }

        return meteoIspis;
    }

    public void setMeteoIspis(List<MeteoIspis> meteoIspis) {
        this.meteoIspis = meteoIspis;
    }

    public String dodajDrzavu() {
        if (popisDrzava == null || popisDrzava.isEmpty()
                || popisDrzavaOdabrano == null || popisDrzavaOdabrano.isEmpty()) {
            return "";
        }
        if (popisOdabranihDrzava == null) {
            popisOdabranihDrzava = new TreeMap<String, Object>();
        }
        for (String d : popisDrzavaOdabrano) {
            popisOdabranihDrzava.put(d, d);
        }
        return "";
    }

    public String obrisiDrzavu() {
        if (popisOdabranihDrzavaOdabrano != null
                && !popisOdabranihDrzavaOdabrano.isEmpty()) {
            for (String d : popisOdabranihDrzavaOdabrano) {
                popisOdabranihDrzava.remove(d);
            }
        }
        return "";
    }

    public String preuzmiGradove() {

        return "";
    }

    public String dodajGrad() {
        if (popisGradovaOdabrano == null
                || popisGradovaOdabrano.isEmpty()) {
            return "";
        }
        if (popisOdabranihGradova == null) {
            popisOdabranihGradova = new TreeMap<String, Object>();
        }
        for (String g : popisGradovaOdabrano) {
            popisOdabranihGradova.put(g, g);
        }
        return "";
    }

    public String obrisiGrad() {
        if (popisOdabranihGradovaOdabrano == null
                || popisOdabranihGradovaOdabrano.isEmpty()) {
            return "";
        }
        for (String g : popisOdabranihGradovaOdabrano) {
            popisOdabranihGradova.remove(g);
        }
        return "";
    }

    public String preuzmiZipKodove() {
        return "";
    }

    public String dodajZipKod() {
        if (popisZipKodovaOdabrano == null
                || popisZipKodovaOdabrano.isEmpty()) {
            return "";
        }
        if (popisOdabranihZipKodova == null) {
            popisOdabranihZipKodova = new TreeMap<String, Object>();
        }
        for (String zk : popisZipKodovaOdabrano) {
            popisOdabranihZipKodova.put(zk, zk);
        }
        return "";
    }

    public String obrisiZipKod() {
        if (popisOdabranihZipKodovaOdabrano == null
                || popisOdabranihZipKodovaOdabrano.isEmpty()) {
            return "";
        }
        for (String zk : popisOdabranihZipKodovaOdabrano) {
            popisOdabranihZipKodova.remove(zk);
        }
        return "";
    }

    public String preuzmiMeteoPodatke() {
        return "";
    }
}
