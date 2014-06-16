
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * Lopeta luokka piirtää pelin lopettamiselementin.
 */
public class Lopeta {
    private static final int X = 25;
    private static final int Y = 535;
    private static final int LEV = 190;
    private static final int KOR = 40;
        
    public static boolean korosta = false;
    
    /**
     * piirra metodi piirtää vaihtaja elementin.
     * Elementti piirretaan korostetus, jos hiiri on sen päällä.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     */
    public static void piirra(Graphics g, Teema teema) {
        g.setFont(new Font("Ariel", Font.BOLD, 20));
        if (korosta) {
            elementti(g, teema.korostettuVaaleaPohja, teema.tummaTeksti, "lopeta");
        } else {
            elementti(g, teema.vaaleaPohja, teema.tummaTeksti, "lopeta");
        }  
    }
    
    /**
     * hiiriPaalla metodi tarkistaa onko hiiri elementin päällä.
     * @param p sisältää hiiren paikkatiedon.
     * @return palautetaan true, jos elementin päällä tai päivastoin.
     */
    public static boolean hiiriPaalla(Point p) {
        return p.x >= X && p.x <= X + LEV && p.y >= Y && p.y <= Y + KOR;
    }
    
    /**
     * elementti metodi on apumetodi, 
     * joka suorittaa varsinaisen elementin piirtamiesen.
     */
    private static void elementti(Graphics g, Color pohja, Color tekstinVari, String teksti) {
            g.setColor(pohja);
            g.fillRect(X, Y, LEV, KOR);
            g.setColor(tekstinVari);
            g.drawString(teksti, X + 20, Y + 27);
    }   
}
