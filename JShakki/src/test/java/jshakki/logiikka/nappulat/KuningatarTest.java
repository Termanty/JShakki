
package jshakki.logiikka.nappulat;

import jshakki.logiikka.Vari;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class KuningatarTest {
    
    @Test
    public void onOikeaNimi() {
        Ruutu nappula = new Kuningatar(Vari.MUSTA, 0, 0);
        assertEquals("Väärä tunnus", 'q', nappula.nimi());
    }
}
