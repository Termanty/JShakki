
package jshakki.logiikka.liikkeet;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class VinoTest {

    @Test
    public void vinojaLiikesuuntiaOnOikeaLukumaara() {
        assertEquals("Liikesuuntien lukumäärä väärin", 4, Vaaka.values().length);
    }
    
    @Test
    public void vinoAskeltenMaaraLiikesuuntaanOikein() {
        for (Liikesuunta l : Vaaka.values()) {
            int[][] s = l.suunnat();
            assertEquals("Siirrot eivät ole kaksiulotteisia. HMM outoa!", 2, s.length);
            assertEquals("Askeleita väärä määrä samaan suuntaan" + l, 7, s[0].length);
        }
    }
}
