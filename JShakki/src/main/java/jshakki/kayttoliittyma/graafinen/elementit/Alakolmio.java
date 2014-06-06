
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.jshakki.OS;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 *
 */
public class Alakolmio {
    
    public static final int X = 230;
    public static final int Y = 435;
    public static final int LEV = 30;
    public static final int KOR = 20;
    public static final int X_LOPPU = X + LEV;
    public static final int Y_LOPPU = Y + KOR;
    
    private static final int kolmioX[] = {X,X+(LEV/2),X_LOPPU};
    private static final int kolmioY[] = {Y,Y_LOPPU,Y};
    
    public static boolean korosta = false;
    
    public static void piirra(Graphics g, Teema teema) {
        if (korosta) {
            alaKolmio(g, teema.korostettuVaaleaPohja);
        } else {
            alaKolmio(g, teema.vaaleaPohja);
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
    
    private static void alaKolmio(Graphics g, Color color) {
        g.setColor(color);
        g.fillPolygon(kolmioX, kolmioY, 3);
    }
}
