
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Graphics;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * Luokka piirtää pohja elementin syödyille nappuloille.
 */
public class SyodytPohja {
    private static final int X = 740;
    private static final int Y = 25;
    private static final int LEV = 220;
    private static final int KOR = 430;
    
    /**
     * piirra metodi piirtää syodyille napuloille pohja elementin.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     */
    public static void piirra(Graphics g, Teema teema) {
        g.setColor(teema.vaaleaPohja);
        g.fillRect(X, Y, LEV, KOR);
    }
}
