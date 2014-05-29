
package jshakki.logiikka.nappulat;

import jshakki.logiikka.Vari;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class SotilasTest {
    
@Test
    public void onOikeaNimi() {
        Ruutu nappula = new Sotilas(Vari.MUSTA);
        assertEquals("Väärä tunnus", 's', nappula.nimi());
    }
}
