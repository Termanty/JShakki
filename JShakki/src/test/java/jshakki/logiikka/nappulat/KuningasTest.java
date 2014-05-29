
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
        Ruutu king = new Kuningas(Vari.MUSTA);
        assertEquals("Kuninkaala väärä tunnus", 'k', king.nimi());
    }
}
