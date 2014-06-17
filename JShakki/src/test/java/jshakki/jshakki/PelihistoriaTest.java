
package jshakki.jshakki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PelihistoriaTest {
    private final Pelihistoria testattava;
    
    public PelihistoriaTest() {
        testattava = new Pelihistoria();
    }
    
    @Test
    public void konstruktroriAlustaaLuokkamuuttujatOikein() {
        assertNotNull(testattava.getSiirrot());
    }
    
    @Test
    public void tallennaSiirtoMetodiLisaaSiirronListalle() {
        testattava.tallennaSiirto(1, 0, 2, 0);
        assertEquals("Siirtoja pitäisi olla listalla yksi", 1, testattava.getSiirrot().size());
        assertEquals("Siirtoa ei kirjattu oikein", "a2a3",testattava.getSiirrot().get(0));
    }
    

}
