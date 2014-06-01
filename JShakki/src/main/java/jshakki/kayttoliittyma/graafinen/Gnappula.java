
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
    Vari vari;
    int kor;
    int lev;

    public Gnappula(Ruutu r, BufferedImage img, int kor, int lev, Vari v) {
        this.r = r;
        this.img = img;
        this.kor = kor;
        this.lev = lev;
        this.vari = v;
    } 
}
