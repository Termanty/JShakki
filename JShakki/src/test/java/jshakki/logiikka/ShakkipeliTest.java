
package jshakki.logiikka;

import java.util.HashMap;
import jshakki.logiikka.nappulat.Kuningas;
import jshakki.logiikka.nappulat.Kuningatar;
import jshakki.logiikka.nappulat.Lähetti;
import jshakki.logiikka.nappulat.Ratsu;
import jshakki.logiikka.nappulat.Ruutu;
import jshakki.logiikka.nappulat.Sotilas;
import jshakki.logiikka.nappulat.Torni;
import jshakki.logiikka.nappulat.Tyhja;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author termanty
 */
public class ShakkipeliTest {
    Shakkipeli testattava;
    
    public ShakkipeliTest() {
        testattava = new Shakkipeli();
    }    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void muutujatKORkeusjaLEVeysOikein() {
        assertEquals("Shakkilaudan ruutujen määrä korkeussuunnassa väärin", 8, testattava.KOR);
        assertEquals("Shakkilaudan ruutujen määrä leveysssuunnassa väärin", 8, testattava.LEV);
    }
    
    @Test
    public void loputLuokkamuutujatOiken() {
        assertFalse("Muutujan peliPäättyi arvo ei ollut false", testattava.loppu());
        assertEquals("Muutujan vuoroNro arvo ei ollut nolla", 1, testattava.vuoroNro());
        assertEquals("Muuttujan Vari arvo ei ollut Vari.VALKOINEN", "VALKOINEN", testattava.vuoro());
    }
    
    @Test
    public void shakkinappulatOikeillaPaikoillaPelinAlussa() {
        sijainnit();
        for (Ruutu r : paikat.keySet()) {
            int[] sijainnit = paikat.get(r);
            for (int i = 0; i < sijainnit.length; i = i + 2) {
                assertEquals(r.nimi() + " väärässä paikassa "+i, r.nimi(), testattava.ruutu(sijainnit[i],sijainnit[i+1]).nimi());
                assertEquals(r.nimi() + " väärässä paikassa "+i, r.vari(), testattava.ruutu(sijainnit[i],sijainnit[i+1]).vari());
            }
        }       
    }
    
    @Test
    public void vaihdaVuoroMetodiVaihtaaValkoisestaMustaan() {
        testattava.vaihdaVuoro();
        assertEquals("Väriä ei vaihdettu valkoisesta mustaan", "MUSTA", testattava.vuoro());   
    }
    
    @Test
    public void vaihdaVuoroMetodiVaihtaaMustastaValkoiseen() {
        testattava.vaihdaVuoro();
        testattava.vaihdaVuoro();
        assertEquals("Väriä ei vaihdettu mustasta vakoiseen", "VALKOINEN", testattava.vuoro());
        
    }
    
    @Test
    public void vaihdaVuoroMetodiKasvattaaVuoroNroOikein() {
        testattava = new Shakkipeli();
        System.out.println(testattava.vuoroNro());
        testattava.vaihdaVuoro();
        assertEquals("Vuoronumero ei olisi pitänyt vaihtaa siirryttäessä valkoisesta mustaan", 1, testattava.vuoroNro());
        testattava.vaihdaVuoro();
        assertEquals("Vuoronumeroa ei vaihdettu oikein", 2, testattava.vuoroNro());         
    }
    
    @Test
    public void siirtoMetodiMuuttaLahtoruudunTyhjaksi() {
        testattava.siirto("a2a3");
        assertEquals("Ruutua a2 ei muutettu tyhjäksi", 'x', testattava.ruutu(1,0).nimi());
    }
    
    @Test
    public void sotilaanSiirtoSuoraanEteenSaantojenMukainen() {
        testattava.siirto("a2a3");
        testattava.siirto("b7b6");
        testattava.siirto("c2c4");
        testattava.siirto("d7d5");
        testattava.siirto("g1h3");
        assertEquals("Sotilas ei liikkunut oikein ruutuun a3", 's', testattava.ruutu(2,0).nimi());
        assertEquals("Sotilas ei liikkunut oikein ruutuun b6", 's', testattava.ruutu(5,1).nimi());
        assertEquals("Sotilas ei liikkunut oikein ruutuun c4", 's', testattava.ruutu(3,2).nimi());
        assertEquals("Sotilas ei liikkunut oikein ruutuun d6", 's', testattava.ruutu(4,3).nimi());
        assertFalse("Aikaisemmin liikkuneen sotilaan tuplasiirtymistä ei estetty", testattava.siirto("a3a5"));
    }
    
    @Test
    public void sotilaanSiirtoVinoonSaantojenMukainen() {
        
    }
    
    @Test
    public void siirtoOmanNappulanHallitsemaanRuutuunEstetty() {
        assertFalse("Siiro c1d2 oman nappulan päälle", testattava.siirto("c1d2"));
    }
    
    
    
    HashMap<Ruutu, int[]> paikat = new HashMap<>();
    private void sijainnit() {
        upseerit(Vari.VALKOINEN, 0);
        soltut(Vari.VALKOINEN, 1);
        upseerit(Vari.MUSTA, 7);
        soltut(Vari.MUSTA, 6);
        tyhjat();
    }

    private void upseerit(Vari vari, int rivi) {
        paikat.put(new Torni(vari), new int[]{rivi,0,rivi,7});
        paikat.put(new Ratsu(vari), new int[]{rivi,1,rivi,6});
        paikat.put(new Lähetti(vari), new int[]{rivi,2,rivi,5});
        paikat.put(new Kuningatar(vari), new int[]{rivi,3});
        paikat.put(new Kuningas(vari), new int[]{rivi,4});
    }
    
    private void soltut(Vari vari, int rivi) {
        paikat.put(new Sotilas(vari), new int[]{rivi,0,rivi,1,rivi,2,rivi,3,rivi,4,rivi,5,rivi,6,rivi,7});
    }

    private void tyhjat() {
        int[] tyhjatRuudut = new int[4 * testattava.LEV * 2];
        int index = 0;
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                tyhjatRuudut[index++] = i;
                tyhjatRuudut[index++] = j;         
            }
        }
        paikat.put(new Tyhja(), tyhjatRuudut);
    }
    
    
    
}
