
package jshakki.kayttoliittyma.graafinen.elementit;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import jshakki.jshakki.Pelihistoria;
import jshakki.kayttoliittyma.graafinen.teema.Teema;

/**
 * Siirrot luokka piirtää tehdyt siirrot listana käyttöliittymään.
 */
public class Siirrot {
    private static final int X = 25;
    private static final int Y = 134;
    
    /**
     * piirra metodi piirtää siiroille pohja elementin.
     * @param g on otus joka osaa piirtää elementtejä.
     * @param teema tietää minkä tyylin mukaisesti elementti piirretään.
     * @param historia tietää listan tehdyistä siirroista.
     */
    public static void piirra(Graphics g, Teema teema, Pelihistoria historia) {
        List<String> siirrot = historia == null ? new ArrayList<String>() : historia.getSiirrot();
        g.setFont(new Font("Ariel", Font.BOLD, 18));
        g.setColor(teema.tummaTeksti);
        int alku = 0;
        if (siirrot.size() > 24) {
            alku = siirrot.size() - 24;
            alku = alku % 2 == 0 ? alku : alku + 1;
            alku -= (Slider.sliderPos * 2);
        }
        for (int i = alku; i < siirrot.size() && i < alku + 24; i++) {
            if (i % 2 == 0) {
                g.drawString("" + (i < 18 ? " ":"")+(i/2+1)+".", X + 10, Y + ((i-alku)*14));
                g.drawString(siirrot.get(i), X + 50, Y + ((i-alku)*14));
            } else {
                g.drawString(siirrot.get(i), X + 120, Y + ((i-alku-1)*14));
            }
        }
    }
}
