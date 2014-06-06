
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.jshakki.OS;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * Ylakolmio luokka piirtää kolmio elementin.
 * Elementtiä klikkaamalla pystyy siirto listalla liikkumaan ylöspäin.
 */
public class Ylakolmio {
    public static final int X = 230;
    public static final int Y = 105;
    public static final int LEV = 30;
    public static final int KOR = 20;
    
    private static final int kolmioX[] = {X,X+LEV/2,X+LEV};
    private static final int kolmioY[] = {Y+KOR,Y,Y+KOR};
    
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
        return p.x + OS.X >= X && p.x + OS.X <= X + LEV && p.y + OS.Y >= Y && p.y + OS.Y <= Y + KOR;
    }
    
    private static void ylaKolmio(Graphics g, Color color) {
        g.setColor(color);
        g.fillPolygon(kolmioX, kolmioY, 3);
    }
}
