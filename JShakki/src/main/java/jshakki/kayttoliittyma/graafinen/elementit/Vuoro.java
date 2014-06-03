
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import jshakki.kayttoliittyma.graafinen.teema.Teema;
import jshakki.logiikka.Shakkipeli;
import jshakki.logiikka.Vari;

/**
 * Vuoro luokka piirtää elementin, joka kertoo vuorossa olevan pelaajan.
 */
public class Vuoro {
    
    public static final int X = 25;
    public static final int Y = 25;
    public static final int LEV = 235;
    public static final int KOR = 65;
    
    /**
     * piirra metodi piirtää vaihtaja elementin.
     * Elementti piirretaan korostetus, jos hiiri on sen päällä.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     * @param peli tuo tiedon pelivuorosta.
     */
    public static void piirra(Graphics g, Teema teema, Shakkipeli peli) {
        g.setFont(new Font("Ariel", Font.BOLD, 28));
        if (peli.vuoro().equals(Vari.VALKOINEN.name())) {
            elemetti(g, "VALKOINEN", teema.tummaTeksti, teema.vaaleaPohja, 50);
        } else {
            elemetti(g, "MUSTA", teema.vaaleaTeksti, teema.tummaPohja, 85);
        }  
    }
    
    /**
     * elementti metodi on apumetodi, 
     * joka suorittaa varsinaisen elementin piirtamiesen.
     */
    private static void elemetti(Graphics g, String teksti, Color tekstinVari, Color taustanVari, int tekstinAloitus) {
        g.setColor(taustanVari);
        g.fillRect(X, Y, LEV, KOR);
        g.setColor(tekstinVari);
        g.drawString(teksti, tekstinAloitus, 70);
    }
}
