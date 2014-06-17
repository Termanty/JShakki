
package jshakki.jshakki;

import jshakki.logiikka.Logiikka;
import jshakki.logiikka.nappulat.Vari;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TekoalyTest {
    private final Logiikka log1;
    private final Logiikka log2;
    private final Tekoaly testattava1;
    private final Tekoaly testattava2;
    
    public TekoalyTest() {
        log1 = new Logiikka(new Pelihistoria());
        log2 = new Logiikka(new Pelihistoria());
        testattava1 = new Tekoaly(Vari.VALKOINEN, log1);
        testattava2 = new Tekoaly(Vari.MUSTA, log2);
    }
    
    @Test
    public void konstruktoriAlustaaLuokkamuuttujatOikein() {
        assertEquals("Pitäisi olla musta", Vari.VALKOINEN, testattava1.vari);
        assertEquals("Pitäisi olla valkoinen", Vari.MUSTA, testattava2.vari);
    }
    
    @Test
    public void ValkoisellePelaajalleTekoalyTekeeSiirron() {
        testattava1.laskeSiirto();
        assertEquals("MUSTA", log1.vuoro());
    }
    
    @Test
    public void MustallePelaajalleTekoalyTekeeSiirron() {
        log2.siirto("a2a3");
        testattava2.laskeSiirto();
        assertEquals("VALKOINEN", log2.vuoro());
    } 
}
