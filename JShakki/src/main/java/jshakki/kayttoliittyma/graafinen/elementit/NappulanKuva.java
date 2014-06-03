
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import jshakki.logiikka.nappulat.Ruutu;

/**
 * NappulanKuva luokka sisältää tarvittavat tiedot sen piirtämiseen.
 */
public class NappulanKuva {
    private final Ruutu nappula;
    public String vari;
    public int kor;
    public int lev;
    public BufferedImage img;

    public NappulanKuva(Ruutu nappula, int kor, int lev, String vari) {
        this.nappula = nappula;
        this.kor = kor;
        this.lev = lev;
        this.vari = vari;
    }
    
    public void vaihdaTeema(BufferedImage image) {
        this.img = image;
    }
    
    public String nimi() {
        return " "+nappula.nimi();
    }
    
    public void piirra(Graphics g) {
        g.drawImage(img, 300+(lev*50), 40+(kor*50), null);
    }
    
    public void piirra(Graphics g, int x, int y) {
        g.drawImage(img, x, y, null);
    }
}
