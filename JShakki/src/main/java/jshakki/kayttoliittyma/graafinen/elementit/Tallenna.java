
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.jshakki.OS;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * Tallenna luokka piirtaa elementin pelin tallennusta varten.
 */
public class Tallenna {
    private static final int X = 25;
    private static final int Y = 480;
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
            elementti(g, teema.korostettuVaaleaPohja, teema.tummaTeksti, "ei toteutettu vielä");
        } else {
            elementti(g, teema.vaaleaPohja, teema.tummaTeksti, "tallenna");
        }  
    }
    
    /**
     * hiiriPaalla metodi tarkistaa onko hiiri elementin päällä.
     * @param p sisältää hiiren paikkatiedon.
     * @return palautetaan true, jos elementin päällä tai päivastoin.
     */
    public static boolean hiiriPaalla(Point p) {
        return p.x + OS.X >= X && p.x + OS.X <= X + LEV && p.y + OS.Y >= Y && p.y + OS.Y <= Y + KOR;
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
