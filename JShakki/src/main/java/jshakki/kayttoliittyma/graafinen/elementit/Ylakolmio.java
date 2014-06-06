
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.jshakki.OS;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 *
 */
public class Ylakolmio {
    
    public static final int X = 230;
    public static final int Y = 105;
    public static final int LEV = 30;
    public static final int KOR = 20;
    public static final int X_LOPPU = X + LEV;
    public static final int Y_LOPPU = Y + KOR;
    
    private static final int kolmioX[] = {230,245,260};
    private static final int kolmioY[] = {125,105,125};
    
    public static boolean korosta = false;
    
    public static void piirra(Graphics g, Teema teema) {
        if (korosta) {
            ylaKolmio(g, teema.korostettuVaaleaPohja);
        } else {
            ylaKolmio(g, teema.vaaleaPohja);
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
    
    private static void ylaKolmio(Graphics g, Color color) {
        g.setColor(color);
        g.fillPolygon(kolmioX, kolmioY, 3);
    }
}
