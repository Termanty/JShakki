
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Graphics;
import java.util.List;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * RuudunKorostaja luokka piirtaa korostukset pelilaudalle.
 * Siirettavan nappulan ympärille piiretään kehikko. Ruudut joihin nappula
 * voi siirtyä korostetaan vihreän sävyisiksi.
 */
public class RuudunKorostaja {
    private final static int KOKO = 50;
    public static int x;
    public static int y;

    public static List<int[]> siirrot;
    
    public static boolean korosta;

    /**
     * piirra metodi piirtää ruutujen korostukset.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää teeman mukaisen korostustyylin.
     */
    public static void piirra(Graphics g, Teema teema) {
        if (korosta) {
            g.setColor(teema.siirettavaNappula);
            g.drawRect(300+(x*KOKO), 40+(y*KOKO), KOKO, KOKO);
            for (int[] s : siirrot) {
                g.setColor(teema.ruudunKorostus);
                g.fillRect(300+((s[1])*KOKO), 40+((7-s[0])*KOKO), KOKO, KOKO);
            }
        }
    }
    
}
