
package jshakki.kayttoliittyma.graafinen;

import java.awt.image.BufferedImage;
import jshakki.logiikka.Vari;
import jshakki.logiikka.nappulat.Ruutu;

/**
 *
 * @author termanty
 */
public class Gnappula {
    private final Ruutu r;
    BufferedImage img;
    String vari;
    int kor;
    int lev;

    public Gnappula(Ruutu r, int kor, int lev, String vari) {
        this.r = r;
        this.kor = kor;
        this.lev = lev;
        this.vari = vari;
    }
    
    public void vaihdaTeema(BufferedImage image) {
        this.img = image;
    }
    
    public String nimi() {
        return " "+r.nimi();
    }
}
