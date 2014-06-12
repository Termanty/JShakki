
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.jshakki.OS;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * Oikeudet luokka sisältää tiedot elementin piirtämiseen
 */
public class Oikeudet {
    
    private static final int X = 740;
    private static final int Y = 535;
    private static final int LEV = 220;
    private static final int KOR = 40;
    
    public static boolean korosta = false;
    
    public static boolean hiiriPaalla(Point p) {
        return p.x + OS.X >= X && p.x + OS.X <= X + LEV && p.y + OS.Y >= Y && p.y + OS.Y <= Y + KOR;
    }
    
    /**
     * piirra metodi piirtää vaihtaja elementin.
     * Elementti piirretaan korostetus, jos hiiri on sen päällä.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     */
    public static void piirra(Graphics g, Teema teema) {
        g.setFont(new Font("Ariel", Font.BOLD, 18));
        if (korosta) {
            elementti(g, teema.korostettuVaaleaPohja, teema.tummaTeksti, "Tero Mäntylä 2014", 15);
        } else {
            elementti(g, teema.vaaleaPohja, teema.tummaTeksti, "copyright", 20);
        }  
    }
    
    /**
     * elementti metodi on apumetodi, 
     * joka suorittaa varsinaisen elementin piirtamiesen.
     */
    private static void elementti(Graphics g, Color pohja, Color tekstinVari, String teksti, int tekstinAlku) {
        g.setColor(pohja);
        g.fillRect(X, Y, LEV, KOR);
        g.setColor(tekstinVari);
        g.drawString(teksti, X + tekstinAlku, Y + 27);
    }
}
