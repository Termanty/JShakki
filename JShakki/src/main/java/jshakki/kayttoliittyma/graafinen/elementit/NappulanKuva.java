
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import jshakki.logiikka.nappulat.Ruutu;

/**
 * NappulanKuva luokka sisältää tarvittavat tiedot sen piirtämiseen.
 */
public class NappulanKuva {
    public final Ruutu nappula;
    public final String vari;
    public BufferedImage image;

    /**
     * Konstruktori luokalle NappulanKuva.
     * @param nappula on logiikkan nappula.
     * @param vari on nappulan väri.
     */
    public NappulanKuva(Ruutu nappula, String vari) {
        this.nappula = nappula;
        this.vari = vari;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    /**
     * Tätä metodia käytetään napulan kuvatiedoston nimen konstruktointiin.
     * @return palauttaa logiikan nimen nappulalle.
     */
    public String nimi() {
        return " " + nappula.nimi();
    }
    
    /**
     * Piirtää nappulan oikeaan paikkaan pelilaudalla.
     * @param g on Swinging Graphics otus.
     */
    public void piirra(Graphics g) {
        piirra(g, 300+(nappula.lev()*50), 40+((7-nappula.kor())*50));
    } 
    
    /**
     * Piirtää nappulan annettuihin koordinaatteihin.
     * @param g on Swinging Graphics otus.
     * @param x on kuvan vasemman yläkulman x sijainti
     * @param y on kuvan vasemman yläkulman y sijainti
     */
    public void piirra(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }
    
    /**
     * Piirtää syödyn nappulan.
     * @param g on Swinging Graphics otus.
     * @param y on kuvan vasemman yläkulman y sijainti
     * @param laskuri tietää monesko syöty nappula tietylle värille.
     * @param etumerkki määrää tuleeko lisärivi edellisen ylä vai alapuolelle.
     * @return 
     */
    public int piirra(Graphics g, int y, int laskuri, int etumerkki) {
        piirra(g, 745 + (laskuri % 4 * 50), y + etumerkki * (laskuri / 4) * 50);
        return ++laskuri;
    }

   
    
    
}
