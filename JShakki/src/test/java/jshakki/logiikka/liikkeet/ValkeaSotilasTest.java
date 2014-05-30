

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
        for (Liikesuunta l : ValkeaSotilas.values()) {
            int[][] s = l.suunnat();
            assertEquals("Siirrot eivät ole kaksiulotteisia. HMM outoa!", 2, s.length);
            assertEquals("Askeleita väärä määrä samaan suuntaan" + l, l.toString().equals("YLOS") ? 2 : 1, s[0].length);
        }
    }
}
