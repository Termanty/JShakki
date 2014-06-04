
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import jshakki.logiikka.nappulat.Ruutu;

/**
 * NappulanKuva luokka sisältää tarvittavat tiedot sen piirtämiseen.
 */
public class NappulanKuva {
    public final Ruutu nappula;
    public String vari;
    public BufferedImage img;

    public NappulanKuva(Ruutu nappula, String vari) {
        this.nappula = nappula;
        this.vari = vari;
    }
    
    public void vaihdaTeema(BufferedImage image) {
        this.img = image;
    }
    
    public String nimi() {
        return " "+nappula.nimi();
    }
    
    public void piirra(Graphics g) {
        g.drawImage(img, 300+(nappula.lev()*50), 40+((7-nappula.kor())*50), null);
    }
    
    public void piirra(Graphics g, int x, int y) {
        g.drawImage(img, x, y, null);
    }
}
