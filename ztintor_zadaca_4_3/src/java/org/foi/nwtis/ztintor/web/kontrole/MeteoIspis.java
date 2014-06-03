/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ztintor.web.kontrole;

/**Klasa koja sadrži objekte čije se vrijednosti ispisuju u tablici meteo Podataka.
 *
 * @author ztintor
 */
public class MeteoIspis {
    
    private String zip;
    private String grad;
    private String temperatura;
    private String vlaznost;
    private double duzina;    
    private double sirina;

    public MeteoIspis(String zip, String grad, String temperatura, String vlaznost, double duzina, double sirina) {
        this.zip = zip;
        this.grad = grad;
        this.temperatura = temperatura;
        this.vlaznost = vlaznost;
        this.duzina = duzina;
        this.sirina = sirina;

    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getVlaznost() {
        return vlaznost;
    }

    public void setVlaznost(String vlaznost) {
        this.vlaznost = vlaznost;
    }

    public double getDuzina() {
        return duzina;
    }

    public void setDuzina(double duzina) {
        this.duzina = duzina;
    }

    public double getSirina() {
        return sirina;
    }

    public void setSirina(double sirina) {
        this.sirina = sirina;
    }

    
}
