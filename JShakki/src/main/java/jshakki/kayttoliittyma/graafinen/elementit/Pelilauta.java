package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import jshakki.jshakki.OS;

/**
 * Pelilauta luokka piirtää elementin ja tarkistaa hiiren oleilun sen päällä.
 */
public class Pelilauta {   
    private static final int X = 280;
    private static final int Y = 20;
    private static final int LEV = 440;
    private static final int KOR = 440;  
    private static final int REUNUS = 20;
    
    public static BufferedImage img;
    
    /**
     * hiiriPaalla metodi tarkistaa onko hiiri elementin päällä.
     * @param p sisältää hiiren paikkatiedon.
     * @return palautetaan true, jos elementin päällä tai päivastoin.
     */
    public static boolean hiiriPaalla(Point p) {
        return p.x + OS.X >= X + REUNUS
                && p.x + OS.X <= X + LEV - REUNUS
                && p.y + OS.Y >= Y + REUNUS
                && p.y + OS.Y <= Y + KOR - REUNUS;
    }
    
    /**
     * piirra metodi piirtää pelilaudan.
     * @param g on otus joka osaa piirtää elementtejä.
     */
    public static void piirra(Graphics g) {
        g.drawImage(img, X, Y, null);
    }
}
