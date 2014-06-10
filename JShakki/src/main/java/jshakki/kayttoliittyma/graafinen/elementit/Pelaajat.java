
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * Pelaajat luokka piirtää aloitusruutuun mustan ja valkoisen elementin.
 */
public class Pelaajat {
    public static final int X = 345;
    public static final int Y = 200;
    public static final int LEV = 150;
    public static final int KOR = 40;
    
    /**
     * piirra metodi piirtää pohja elementin.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     */
    public static void piirra(Graphics g, Teema teema) {
        g.setFont(new Font("Ariel", Font.BOLD, 18));
        g.setColor(Color.WHITE);
        g.fillRect(X, Y, LEV, KOR);
        g.setColor(Color.BLACK);
        g.drawString("valkoinen", X + 14, Y + 27);
        
        g.setColor(Color.DARK_GRAY);
        g.fillRect(X + 160, Y, LEV, KOR);
        g.setColor(Color.WHITE);
        g.drawString("musta", X + 160 + 20, Y + 27);

    }
}
