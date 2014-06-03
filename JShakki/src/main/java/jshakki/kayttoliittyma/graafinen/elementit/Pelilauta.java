package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import jshakki.jshakki.OS;

/**
 * Pelilauta luokka piirtää elementin ja tarkistaa hiiren oleilun sen päällä.
 */
public class Pelilauta {
    
    public static final int X = 280;
    public static final int Y = 20;
    public static final int LEV = 440;
    public static final int KOR = 440;
    public static final int X_LOPPU = X + LEV;
    public static final int Y_LOPPU = Y + KOR;
    
    public static final int REUNUS = 20;
    
    public static BufferedImage img;
    
    /**
     * hiiriPaalla metodi tarkistaa onko hiiri elementin päällä.
     * @param p sisältää hiiren paikkatiedon.
     * @return palautetaan true, jos elementin päällä tai päivastoin.
     */
    public static boolean hiiriPaalla(Point p) {
        return p.x + REUNUS + OS.X >= X 
                && p.x - REUNUS + OS.X <= X_LOPPU 
                && p.y + REUNUS + OS.Y >= Y 
                && p.y - REUNUS + OS.Y <= Y_LOPPU;
    }
    
    /**
     * piirra metodi piirtää pelilaudan.
     * @param g on otus joka osaa piirtää elementtejä.
     */
    public static void piirra(Graphics g) {
        g.drawImage(img, X, Y, null);
    }
}
