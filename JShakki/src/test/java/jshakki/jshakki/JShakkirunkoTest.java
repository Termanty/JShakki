
package jshakki.jshakki;

import jshakki.logiikka.nappulat.Vari;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JShakkirunkoTest {
    private final JShakkirunko testattava;
    
    public JShakkirunkoTest() {
        testattava = new JShakkirunko();
    }
    
    @Before
    public void setUp() {   
    }
    
    @Test
    public void konstruktroriAlustaaLuokkamuuttujatOikein() {
        assertTrue("Aloitustila ei ollut true", testattava.aloitustila);
        assertFalse("Tekolalyn vuoro ei ole false", testattava.tekoalynVuoro);
        assertEquals("Valkoisen pelaajan tekoalyn pitäisi olla null", null, testattava.getValkoinen());
        assertEquals("Mustan pelaajan tekoalyn pitäisi olla null", null, testattava.getMusta());
        assertNotNull("Historia luokkaa ei ole alustettu", testattava.logiikka);
        assertNotNull("Historia luokkaa ei ole alustettu", testattava.historia);
    }
    
    @Test
    public void uusiPeliMetodiSiirtääPelitilaan() {
        testattava.uusiPeli();
        assertFalse("Aloitustila ei ollut false", testattava.aloitustila);
    }
    
    @Test
    public void uusiPeliTekeeValkoiselleTekoalylleSiirron() {
        testattava.setValkoinen();
        testattava.uusiPeli();
        assertEquals("Valkoinen tekoaly teki siirron", "MUSTA", testattava.logiikka.vuoro());
    }
    
    @Test
    public void uusiPeliMetodiEiSiirräIhmisenPelaamaaValkoista() {
        testattava.uusiPeli();
        assertEquals("Valkoisia siirreettiin vaikka sitä ei pitäisi ohjata tekoaly", "VALKOINEN", testattava.logiikka.vuoro());
    }
    
    @Test
    public void lopetaPeliMetodiSiirtääAoitustilaan() {
        testattava.setValkoinen();
        testattava.uusiPeli();
        testattava.lopetaPeli();
        assertTrue("Aloitustila ei ollut true", testattava.aloitustila);
        assertFalse("Tekolalyn vuoro ei ole false", testattava.tekoalynVuoro);
        assertEquals("Valkoisen pelaajan tekoalyn pitäisi olla null", null, testattava.getValkoinen());
        assertEquals("Mustan pelaajan tekoalyn pitäisi olla null", null, testattava.getMusta());
        assertNotNull("Historia luokkaa ei ole alustettu", testattava.logiikka);
        assertNotNull("Historia luokkaa ei ole alustettu", testattava.historia);
    }
    
    @Test
    public void siirtoMetodiTekeeSiirronValkoiselle() {
        testattava.uusiPeli();
        assertTrue("Siirtoa a2a3 ei hyväksytty", testattava.siirto(1, 0, 2, 0));
    }
    
    @Test
    public void siirtoMetodiHylkääVirheellisenValkoisenSiirron() {
        testattava.uusiPeli();
        assertFalse("Säätöjen vastainen siirtoa a2a5 palautitti Truen", testattava.siirto(1, 0, 4, 0));
    }
    
    @Test
    public void siirtoMetodiTekeeSiirronMustalle() {
        testattava.setValkoinen();
        testattava.uusiPeli();
        assertTrue("Siirtoa a7a6 ei hyväksytty", testattava.siirto(6, 0, 5, 0));
    }
    
    @Test
    public void siirtoMetodiHylkääVirheellisenMustanSiirron() {
        testattava.setValkoinen();
        testattava.uusiPeli();
        assertFalse("Säätöjen vastainen siirtoa a7a4 palautitti Truen", testattava.siirto(6, 0, 3, 0));
    }
    
    @Test
    public void siirtoMetodiTekeeSiirronValkoiselleTekoalylle() {
        testattava.setValkoinen();
        testattava.uusiPeli();
        testattava.siirto(6, 0, 5, 0);
        assertEquals("Pitäisi olla mustan vuoro", Vari.MUSTA.toString().toUpperCase(), testattava.logiikka.vuoro());
    }
    
    @Test
    public void siirtoMetodiTekeeSiirronMustalleTekoalylle() {
        testattava.setMusta();
        testattava.uusiPeli();
        testattava.siirto(1, 0, 2, 0);
        assertEquals("Pitäisi olla valkoisen vuoro", Vari.VALKOINEN.toString().toUpperCase(), testattava.logiikka.vuoro());
    }
    
    @Test
    public void setValkoinenMetodiVaihtaaTekoalylleJaPoisSiitta() {
        testattava.setValkoinen();
        assertNotNull(testattava.getValkoinen());
        testattava.setValkoinen();
        assertEquals(null, testattava.getValkoinen());
    }
    
    @Test
    public void setMustaMetodiVaihtaaTekoalylleJaPoisSiitta() {
        testattava.setMusta();
        assertNotNull(testattava.getMusta());
        testattava.setMusta();
        assertEquals(null, testattava.getMusta());
    }
    
    
}
