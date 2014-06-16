
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * Alakolmio luokka piirtää kolmio elementin.
 * Elementtiä klikkaamalla pystyy siirto listalla liikkumaan alaspäin.
 */
public class Alakolmio {    
    private static final int X = 230;
    private static final int Y = 435;
    private static final int LEV = 30;
    private static final int KOR = 20;
    
    private static final int kolmioX[] = {X,X+(LEV/2),X + LEV};
    private static final int kolmioY[] = {Y,Y + KOR,Y};
    
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
        return p.x >= X && p.x <= X + LEV && p.y >= Y && p.y <= Y + KOR;
    }
    
    private static void alaKolmio(Graphics g, Color color) {
        g.setColor(color);
        g.fillPolygon(kolmioX, kolmioY, 3);
    }
}
