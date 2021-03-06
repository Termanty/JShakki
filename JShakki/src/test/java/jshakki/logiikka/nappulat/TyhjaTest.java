
package jshakki.logiikka.nappulat;

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
        assertFalse(nappula.vastustaja(new Torni(Vari.MUSTA, 0, 0)));
        assertFalse(nappula.vastustaja(new Torni(Vari.VALKOINEN, 0, 0)));
    }
    
    @Test
    public void siirtoLaskuriEiKasvataSiirtojenmaaraa() {
        nappula.kasvataSiirtoLaskuria();
        assertEquals(-1, nappula.siirtojenMaara());
    }
    
    @Test
    public void korPalauttaaNollan() {
        assertEquals(0, nappula.kor());
    }
    
    @Test
    public void levPalauttaaNollan() {
        assertEquals(0, nappula.lev());
    }
    
    @Test
    public void uusiSijaintiMetodiEiMuutaKorLevArvoja() {
        nappula.uusiSijainti(1, 1);
        assertEquals(0, nappula.kor());
        assertEquals(0, nappula.lev());
    }
}
