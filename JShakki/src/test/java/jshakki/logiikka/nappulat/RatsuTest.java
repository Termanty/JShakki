
package jshakki.logiikka.nappulat;

import jshakki.logiikka.Vari;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class RatsuTest {
    
@Test
    public void onOikeaNimi() {
        Ruutu nappula = new Ratsu(Vari.MUSTA);
        assertEquals("Väärä tunnus", 'r', nappula.nimi());
    }
}
