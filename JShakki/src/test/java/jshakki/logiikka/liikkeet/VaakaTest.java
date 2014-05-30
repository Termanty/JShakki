
package jshakki.logiikka.liikkeet;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class VaakaTest {

    @Test
    public void vaakaLiikesuuntiaOnOikeaLukumaara() {
        assertEquals("Liikesuuntien lukumäärä väärin", 4, Vaaka.values().length);
    }
    
    @Test
    public void vaakaAskeltenMaaraLiikesuuntaanOikein() {
        for (Liikesuunta l : Vaaka.values()) {
            int[][] s = l.suunnat();
            assertEquals("Siirrot eivät ole kaksiulotteisia. HMM outoa!", 2, s.length);
            assertEquals("Askeleita väärä määrä samaan suuntaan" + l, 7, s[0].length);
        }
    }
}
