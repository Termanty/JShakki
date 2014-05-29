

package jshakki.logiikka.liikkeet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class ValkeaSotilasTest {
    
    @Test
    public void valkeanSotilaanLiikesuuntiaOnOikeaLukumaara() {
        assertEquals("Liikesuuntien lukumäärä väärin", 3, ValkeaSotilas.values().length);
    }
    
    @Test
    public void valkeanSotilaanAskeltenMaaraLiikesuuntaanOikein() {
        for (Liike l : ValkeaSotilas.values()) {
            int[][] s = l.siirrot();
            assertEquals("Siirrot eivät ole kaksiulotteisia. HMM outoa!", 2, s.length);
            assertEquals("Askeleita väärä määrä samaan suuntaan" + l, l.toString().equals("YLOS") ? 2 : 1, s[0].length);
        }
    }
}
