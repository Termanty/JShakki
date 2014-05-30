
package jshakki.logiikka.liikkeet;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class HyppyTest {

    @Test
    public void ratsunLiikesuuntiaOnOikeaLukumaara() {
        assertEquals("Ratsun liikeiden lukumäärä väärin", 8, Hyppy.values().length);
    }
    
    @Test
    public void kuninkaanAskeltenMaaraLiikesuuntaanOikein() {
        for (Liikesuunta l : Hyppy.values()) {
            int[][] s = l.suunnat();
            assertEquals("Siirrot eivät ole kaksiulotteisia. HMM outoa!", 2, s.length);
            assertEquals("Hyppyjä useita samaan suuntaan" + l, 1, s[0].length);
        }
    }
}
