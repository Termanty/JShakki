
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Font;
import java.awt.Graphics;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * PeliLoppu luokka sisältää tiedot peli loppu elementin piirtämiseen.
 */
public class PeliLoppu {
    private static final int X = 350;
    private static final int Y = 480;
    private static final int LEV = 300;
    private static final int KOR = 100;
    
    /**
     * piirra metodi piirtää syodyille napuloille pohja elementin.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     */
    public static void piirra(Graphics g, Teema teema) {
        g.setColor(teema.vaaleaPohja);
        g.fillRect(X, Y, LEV, KOR);
        g.setFont(new Font("Ariel", Font.BOLD, 28));
        g.setColor(teema.tummaTeksti);
        g.drawString("PELI LOPPU", X + 65, Y + 60);
    }
}
