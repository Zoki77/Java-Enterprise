/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ztintor.ejb.sb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.ws.WebServiceRef;
import net.wxbug.api.LiveWeatherData;
import net.wxbug.api.UnitType;
import net.wxbug.api.WeatherBugWebServices;
import org.foi.nwtis.ztintor.konfiguracije.Konfiguracija;
import org.foi.nwtis.ztintor.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.ztintor.konfiguracije.NemaKonfiguracije;

/**
 *
 * @author zoran
 */
@Stateless
@LocalBean
public class WeatherBugKlijent {
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/api.wxbug.net/weatherservice.asmx.wsdl")
    private WeatherBugWebServices service;

    private String mjWeatherBugKod;
    private Konfiguracija konfig;

    public LiveWeatherData dajMeteoPodatke(String zip) {
        try {
            String kod = getClass().getResource("konf.xml").getPath();
            konfig = KonfiguracijaApstraktna.preuzmiKonfiguraciju(kod);
            mjWeatherBugKod = konfig.dajPostavku("kod");
            return getLiveWeatherByUSZipCode(zip, UnitType.METRIC, mjWeatherBugKod);
        } catch (NemaKonfiguracije ex) {
            System.out.println("Konfiguracija nije preuzeta.");
            return null;
        }

    }

    private LiveWeatherData getLiveWeatherByUSZipCode(java.lang.String zipCode, net.wxbug.api.UnitType unittype, java.lang.String aCode) {
        net.wxbug.api.WeatherBugWebServicesSoap port = service.getWeatherBugWebServicesSoap();
        return port.getLiveWeatherByUSZipCode(zipCode, unittype, aCode);
    }

}
