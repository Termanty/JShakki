
package jshakki.kayttoliittyma.graafinen;

import java.awt.image.BufferedImage;
import jshakki.logiikka.nappulat.Ruutu;

/**
 *
 * @author termanty
 */
public class Gnappula {
    private final Ruutu r;
    BufferedImage img;
    int kor;
    int lev;

    public Gnappula(Ruutu r, BufferedImage img, int kor, int lev) {
        this.r = r;
        this.img = img;
        this.kor = kor;
        this.lev = lev;
    } 
}
