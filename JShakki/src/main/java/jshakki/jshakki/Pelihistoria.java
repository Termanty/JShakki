

package jshakki.jshakki;

import java.util.ArrayList;
import java.util.List;

/**
 * Pelihistoria luokka pitää kirjaa tehdyistä siirroita.
 */
public class Pelihistoria {
    private List<String> siirrot;

    public Pelihistoria() {
        siirrot = new ArrayList<>();
    }
    
    public void tallennaSiirto(int kor, int lev, int korMin, int levMin) {
        siirrot.add(merkkijonona(kor, lev, korMin, levMin));
    }

    public List<String> getSiirrot() {
        return siirrot;
    }

    private String merkkijonona(int kor, int lev, int korMin, int levMin) {
        return "" + (char)(lev + 'a') + (kor+1) + (char)(levMin + 'a') + (korMin+1);
    }
     
}
