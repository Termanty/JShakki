
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Graphics;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * Siirro luokka piirtää pohjan siirtokirjanpidolle.
 */
public class SiirrotPohja {
    private static final int X = 25;
    private static final int Y = 105;
    private static final int LEV = 190;
    private static final int KOR = 350;
    
    /**
     * piirra metodi piirtää siiroille pohja elementin.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     */
    public static void piirra(Graphics g, Teema teema) {
        g.setColor(teema.vaaleaPohja);
        g.fillRect(X, Y, LEV, KOR);
    }
}
