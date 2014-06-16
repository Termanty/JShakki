

package jshakki.jshakki;

import java.util.ArrayList;
import java.util.List;

/**
 * Pelihistoria luokka pitää kirjaa tehdyistä siirroita.
 */
public class Pelihistoria {
    /**
     * Lista tehdyistä siirroista.
     */
    private final List<String> siirrot;
    
    /**
     * Konstruktori.
     */
    public Pelihistoria() {
        siirrot = new ArrayList<>();
    }

    /**
     * Metodi tallentaa siirron listalle.
     * @param korMista on nappulan nykyinen ruutu korkeussuunnassa.
     * @param levMista on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMinne on uusi ruutu korkeussuunnassa.
     * @param levMinne on uusi ruutu leveyssuunnassa.
     */
    public void tallennaSiirto(int korMista, int levMista, int korMinne, int levMinne) {
        siirrot.add(merkkijonona(korMista, levMista, korMinne, levMinne));
    }
 
    public List<String> getSiirrot() {
        return siirrot;
    }

    /**
     * Muuntaa siirron koordinaatti-informaation merkkijonoksi.
     * @param korMista on nappulan nykyinen ruutu korkeussuunnassa.
     * @param levMista on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMinne on uusi ruutu korkeussuunnassa.
     * @param levMinne on uusi ruutu leveyssuunnassa.
     */
    private String merkkijonona(int korMista, int levMista, int korMinne, int levMinne) {
        return "" + (char)(levMista + 'a') + (korMista+1) + (char)(levMinne + 'a') + (korMinne+1);
    }
     
}
