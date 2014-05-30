
package jshakki.logiikka.liikkeet;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class AskelTest {

    @Test
    public void kuninkaanLiikesuuntiaOnOikeaLukumaara() {
        assertEquals("Askelten lukumäärä väärin", 8, Askel.values().length);
    }
    
    @Test
    public void kuninkaanAskeltenMaaraLiikesuuntaanOikein() {
        for (Liikesuunta l : Askel.values()) {
            int[][] s = l.suunnat();
            assertEquals("Siirrot eivät ole kaksiulotteisia. HMM outoa!", 2, s.length);
            assertEquals("Kuninkaalla useita askelia suuntaan" + l, 1, s[0].length);
        }
    }
}
