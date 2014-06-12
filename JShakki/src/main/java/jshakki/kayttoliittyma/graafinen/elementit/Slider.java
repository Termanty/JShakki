

package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.jshakki.OS;
import jshakki.jshakki.Pelihistoria;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * SiirrotSlider luokka piirtää elementit siirtolistan katselua varten.
 */
public class Slider {
    
    private static final int X = 230;
    private static final int Y = 135;
    private static final int LEV = 30;
    private static final int KOR = 290;
    
    private static int y = 135;
    private static int kor = 290;
    private static int jako = 0;
    public static int sliderPos = 0;
    
    public static boolean korosta = false;
    
    /**
     * piirra metodi piirtää elementin siirtolistan rullaamiseen.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     */
    public static void piirra(Graphics g, Teema teema, Pelihistoria historia) {
        int maara = historia == null ? 0 : historia.getSiirrot().size();
        jako = (maara+1)/2;
        if (maara > 18) {
            kor = 12 * KOR / jako;
            y = Y + (KOR - kor) - (sliderPos * KOR / jako);
        }
        if (korosta) {
            slider(g, teema.korostettuVaaleaPohja);
        } else {
            slider(g, teema.vaaleaPohja);
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

    private static void slider(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(X, y, LEV, kor);
    }
    
    public static void kasvata() {
        if (sliderPos < jako - 12) {
            sliderPos++;
        }
    }
    
    public static void vahenna() {
        if (sliderPos > 0) {
            sliderPos--;
        }
    }
    
    public static void nollaa() {
        y = 135;
        kor = 290;
        jako = 0;
        sliderPos = 0;
    }  
}
