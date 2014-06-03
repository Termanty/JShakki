
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Taustakuva luokka piirtää teeman mukaisen taustakuvan. 
 */
public class Taustakuva {
    public static final int X = 0;
    public static final int Y = 0;
    public static BufferedImage img;
     
    /**
     * piirra metodi piirtää taustakuvan.
     * @param g on otus joka osaa piirtää elementtejä.
     */
    public static void piirra(Graphics g) {
        g.drawImage(img, X, Y, null);
    }
}
