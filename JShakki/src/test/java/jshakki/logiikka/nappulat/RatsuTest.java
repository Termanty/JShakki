
package jshakki.logiikka.nappulat;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class RatsuTest {
    
@Test
    public void onOikeaNimi() {
        Ruutu nappula = new Ratsu(Vari.MUSTA, 0, 0);
        assertEquals("Väärä tunnus", 'r', nappula.nimi());
    }
}
