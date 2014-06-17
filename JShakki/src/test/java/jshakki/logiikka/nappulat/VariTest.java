

package jshakki.logiikka.nappulat;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class VariTest {

    @Test
    public void shakkinappuloidenVaritLoytyy() {
        assertEquals("Väriä ei löytynyt", "valkoinen", Vari.VALKOINEN.toString());
        assertEquals("Väriä ei löytynyt", "musta", Vari.MUSTA.toString());
    }
}
