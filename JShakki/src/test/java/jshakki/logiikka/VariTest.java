

package jshakki.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class VariTest {

    @Test
    public void shakkinappuloidenVaritLoytyy() {
        assertEquals("Väriä ei löytynyt", "valkoinen", Vari.VALKOINEN.toString());
        assertEquals("Väriä ei löytynyt", "musta", Vari.MUSTA.toString());
    }
}
