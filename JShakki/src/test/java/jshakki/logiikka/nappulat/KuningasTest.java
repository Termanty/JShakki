
package jshakki.logiikka.nappulat;

import jshakki.logiikka.Vari;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class KuningasTest {
    
    @Test
    public void kuninkaallaOnOikeaNimi() {
        Ruutu king = new Kuningas(Vari.MUSTA, 0, 0);
        assertEquals("Kuninkaala väärä tunnus", 'k', king.nimi());
    }
}
