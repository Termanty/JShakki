
package jshakki.logiikka.nappulat;

import jshakki.logiikka.Vari;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class TorniTest {
    
@Test
    public void onOikeaNimi() {
        Ruutu nappula = new Torni(Vari.MUSTA);
        assertEquals("Väärä tunnus", 't', nappula.nimi());
    }
}
