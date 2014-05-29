
package jshakki.logiikka.liikkeet;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class MustaSotilasTest {
    
    @Test
    public void mustanSotilaanLiikesuuntiaOnOikeaLukumaara() {
        assertEquals("Liikesuuntien lukumäärä väärin", 3, MustaSotilas.values().length);
    }
    
    @Test
    public void mustanSotilaanAskeltenMaaraLiikesuuntaanOikein() {
        for (Liike l : MustaSotilas.values()) {
            int[][] s = l.siirrot();
            assertEquals("Siirrot eivät ole kaksiulotteisia. HMM outoa!", 2, s.length);
            assertEquals("Askeleita väärä määrä samaan suuntaan" + l, l.toString().equals("ALAS") ? 2 : 1, s[0].length);
        }
    }
}
