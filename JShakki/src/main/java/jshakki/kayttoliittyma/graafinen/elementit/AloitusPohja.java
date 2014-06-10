
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Color;
import java.awt.Graphics;
import jshakki.kayttoliittyma.graafinen.teema.Lataa;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * AloitusPohja luokka sisältää tiedon Aloitusruudun piirtämiseen.
 */
public class AloitusPohja {
    private static final int X = 140;
    private static final int Y = 40;
    private static final int LEV = 720;
    private static final int KOR = 60;
    
    /**
     * piirra metodi piirtää pohja elementin.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     */
    public static void piirra(Graphics g, Teema teema, Lataa hae) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(X, Y, LEV, KOR);
        g.drawImage(hae.kuva("images/aloitus3.jpg"), X, Y+60, null);
//        g.fillRect(X, Y, LEV, KOR);
    }
}
