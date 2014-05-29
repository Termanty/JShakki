
package jshakki.logiikka.nappulat;

import jshakki.logiikka.Vari;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class TyhjaTest {
    Ruutu nappula;
    
    public TyhjaTest() {
        nappula = new Tyhja();
    }
     
    @Test
    public void onOikeaNimi() {
        assertEquals("Väärä tunnus", 'x', nappula.nimi());
    }
    
    @Test
    public void onOikeaMaaraSiirtoja() {
        assertEquals(-1, nappula.siirtojenMaara());
    }
    
    @Test
    public void palauttaaVarinNull() {
        assertEquals(null, nappula.vari());
    }
    
    @Test
    public void palauttaaLiikkeetNull() {
        assertEquals(null, nappula.liikkeet());
    }
    
    @Test
    public void palauttaaVastustajaNull() {
        assertFalse(nappula.vastustaja(new Torni(Vari.MUSTA)));
        assertFalse(nappula.vastustaja(new Torni(Vari.VALKOINEN)));
    }
    
    @Test
    public void siirtoLaskuriEiKasvataSiirtojenmaaraa() {
        nappula.kasvataSiirtoLaskuria();
        assertEquals(-1, nappula.siirtojenMaara());
    }
}
