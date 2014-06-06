

package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.jshakki.JShakkirunko;
import jshakki.jshakki.OS;
import static jshakki.kayttoliittyma.graafinen.elementit.Alakolmio.X_LOPPU;
import static jshakki.kayttoliittyma.graafinen.elementit.Alakolmio.Y_LOPPU;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * SiirrotSlider luokka piirtää elementit siirtolistan katselua varten.
 */
public class Slider {
    
    public static final int X = 230;
    public static final int Y = 135;
    public static final int LEV = 30;
    public static final int KOR = 290;
    public static final int X_LOPPU = X + LEV;
    public static final int Y_LOPPU = Y + KOR;
    
    public static boolean korosta = false;
    
    /**
     * piirra metodi piirtää elementin siirtolistan rullaamiseen.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     */
    public static void piirra(Graphics g, Teema teema, JShakkirunko peli) {
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
        return p.x + OS.X >= X && p.x + OS.X <= X_LOPPU && p.y + OS.Y >= Y && p.y + OS.Y <= Y_LOPPU;
    }

    private static void slider(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(X, Y, LEV, KOR);
    }
    
}
