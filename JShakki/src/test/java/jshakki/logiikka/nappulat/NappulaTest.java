
package jshakki.logiikka.nappulat;

import jshakki.logiikka.Vari;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class NappulaTest {
    Nappula testattava;
    
    public NappulaTest() {
        testattava = new Nappula(Vari.MUSTA, 'z');
    }
    
    @Test
    public void nappulanNimiAlustettiinKonstruktorissaOikein() {
        assertEquals("Nimi meni väärin", 'z', testattava.nimi());
    }
    
    @Test
    public void nappulanVariAlustettiinKonstruktorissaOikein() {
        assertEquals("Vari meni väärin", "MUSTA", testattava.vari().name());
    }
    
    @Test
    public void nappulanSiirtoLaskuriAlustettuOikein() {
        assertEquals("Siirtojen lukumäärä väärin", 0, testattava.siirtojenMaara());
    }
    
    @Test
    public void nappulanSiirtoLaskuriKasvattaaLukemaa() {
        testattava.kasvataSiirtoLaskuria();
        assertEquals("Laskurin arvo ei kasvanut oikein", 1, testattava.siirtojenMaara());
    }
    
    @Test
    public void nappulanLiikkeetArrayListOnOlemassa() {
        assertTrue("ArrayList ei ollut tyhjä", testattava.liikkeet().isEmpty());
    }
    
    @Test
    public void vastustajaPalauttaaOikeanArvon() {
        assertTrue(" ", testattava.vastustaja(new Nappula(Vari.VALKOINEN, 'x')));
        assertFalse(" ", testattava.vastustaja(new Nappula(Vari.MUSTA, 'x')));
        assertFalse(" ", new Nappula(Vari.VALKOINEN, 'x').vastustaja(new Nappula(Vari.VALKOINEN, 'x')));
        assertTrue(" ", new Nappula(Vari.VALKOINEN, 'x').vastustaja(new Nappula(Vari.MUSTA, 'x')));
    }  
}
