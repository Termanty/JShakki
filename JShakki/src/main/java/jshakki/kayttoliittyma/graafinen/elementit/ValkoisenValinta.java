
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.jshakki.JShakkirunko;
import jshakki.jshakki.OS;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * ValkoisenValinta luokka sisältää tiedot elementin piirtämiseen.
 */
public class ValkoisenValinta {
    private static final int X = 435;
    private static final int Y = 365;
    private static final int LEV = 130;
    private static final int KOR = 40;
    
    private static final String ihminen = " IHMINEN";
    private static final String tk = "TIETOKONE";

    public static boolean korosta = false;
    
    public static boolean hiiriPaalla(Point p) {
        return p.x + OS.X >= X && p.x + OS.X <= X + LEV && p.y + OS.Y >= Y && p.y + OS.Y <= Y + KOR;
    }
    
    /**
     * piirra metodi piirtää vaihtaja elementin.
     * Elementti piirretaan korostetus, jos hiiri on sen päällä.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     * @param peli valkoisella pelaavan tekoäly päällä.
     */
    public static void piirra(Graphics g, Teema teema, JShakkirunko peli) {
        g.setFont(new Font("Ariel", Font.BOLD, 18));
        if (korosta) {
            elementti(g, Color.WHITE, Color.DARK_GRAY, peli.getValkoinen() != null ? ihminen : tk, 8);
        } else {
            elementti(g, Color.WHITE, Color.DARK_GRAY, peli.getValkoinen() == null ? ihminen : tk, 8);
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
