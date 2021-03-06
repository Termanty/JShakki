
package jshakki.logiikka;

import jshakki.logiikka.nappulat.Vari;
import java.util.HashMap;
import jshakki.jshakki.Pelihistoria;
import jshakki.logiikka.nappulat.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author termanty
 */
public class LogiikkaTest {
    Logiikka testattava;
    Pelihistoria historia;
    
    public LogiikkaTest() {
        historia = new Pelihistoria();
        testattava = new Logiikka(historia);
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
        assertFalse(testattava.paivita());
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
    public void kuittauksenJalkeenPaivitaMuuttujaArvoFalse() {
        testattava.kuittaa();
        assertFalse(testattava.paivita());  
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
        testattava = new Logiikka(new Pelihistoria());
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
    public void siirtoOmanNappulanHallitsemaanRuutuunEstetty() {
        assertFalse("Siiro c1d2 oman nappulan päälle", testattava.siirto("c1d2"));
    }
    
    @Test
    public void sotilaanSiirtoSuoraanEteenpain() {
        teeSiirrot("a2a3 b7b6 c2c4 d7d5 a3a4");
        assertEquals("Sotilas ei liikkunut oikein ruutuun a4", 's', testattava.ruutu("a4").nimi());
        assertEquals("Sotilas ei liikkunut oikein ruutuun b6", 's', testattava.ruutu("b6").nimi());
        assertEquals("Sotilas ei liikkunut oikein ruutuun c4", 's', testattava.ruutu("c4").nimi());
        assertEquals("Sotilas ei liikkunut oikein ruutuun d6", 's', testattava.ruutu("d5").nimi());
    }
    
    @Test
    public void sotilaanVirheellisetSiirrotSuoraanEteenEstetty() {
        teeSiirrot("a2a4 a7a5 b2b3 c7c6 e7e6");
        testaaSiirrot("a4a5 a4a6 b3b5 c2c5 d2d6 e2e7 f2f8", "Virheelline siirto hyväksyttiin", false);
        teeSiirrot("e2e4");
        testaaSiirrot("a5a4 a5a3 c6c3 e6e3 f7f2 h7h1", "Virheelline siirto hyväksyttiin", false);
    }
    
    @Test
    public void sotilaanSiirtoVinoonSaantojenMukainen() {
        testaaSiirrot("a2a3 a7a5 b2b4 h7h5 g2g4 g7g6", "Virhe sotilaan siirrossa suoraan eteen", true);
        testaaSiirrot("g4h5 a5b4 h5g6 b4a3", "Virhe sotilaan siirrossa vinoon eteen", true);
    }
    
    @Test
    public void sotilaanVirheelisetSiirrotVinoonEstetty() {
        teeSiirrot("a2a4 b7b5 c2c4 d7d6");
        testaaSiirrot("b2a3 b2c3 c4d5", "Virhe siirrossa", false);
        teeSiirrot("a4a5");
        testaaSiirrot("d6c5 d6e5 ", "Virhe siirrossa", false);
    }
    
    @Test
    public void sotilaanKorottaminenToimii() {
        teeSiirrot("a2a4 b7b5 a4b5 b8a6 b5b6 c7c5 b6b7 c5c4 b7b8 c4c3 b8b3 c3b2 b3h3 b2a1");
        assertEquals("Sotilaan korotus ei onnistunut", 'q', testattava.ruutu("a1").nimi());
        assertEquals("Sotilaan korotus ei onnistunut", 'q', testattava.ruutu("h3").nimi());
    }
    
    @Test
    public void kuninkaanLaillisetSiirrotOikein() {
        testaaSiirrot("e2e3 a7a6 e1e2 a6a5 e2d3 b7b5 d3c3 a5a4 c3b4 c7c6 b4b5 "
                + "h7h5 b5c5 h5h4 c5d6 h4h3 d6e5 h3g2 e5e4 g7g5 e4d3", "Virhe siirrossa", true);
    }
    
    @Test
    public void kuninkaanVirheelisetSiirrotEstetty() {
        teeSiirrot("e2e3 a7a6 e1e2 a6a5 e2d3 b7b5");
        testaaSiirrot("d3d2 d3c2 d3e3 d3b3 d3c5 d3d5 d3f3 d3e5", "Virhe siirrossa", false);
    }
    
    @Test
    public void kuningattarenLaillisetSiirrotOikein() {
        testaaSiirrot("d2d4 e7e6 d1d3 d8g5 d3h3 g5a5 h3h7 a5c3 h7g8 c3b2 g8f8 b2c2", "Virhe siirrossa", true);
    }
    
    @Test
    public void kuningattarenVirheelisetSiirrotEstetty() {
        teeSiirrot("d2d4 e7e5 d1d3 d8g5");
        testaaSiirrot("d3c2 d3e2 d3d4 d3b1 d3c5 d3e5", "Virhe siirrossa", false);
    }
    
    @Test
    public void lahetinLaillisetSiirrotOikein() {
        testaaSiirrot("e2e3 a7a5 f1e2 e7e6 e2f3 f8e7 f3h5 e7c5 h5f7 c5e3 f7e6", "Virhe siirrossa", true);
    }
    
    @Test
    public void lahetinVirheelisetSiirrotEstetty() {
        teeSiirrot("e2e3 e6e7 f1e2 f8e7");
        testaaSiirrot("e2d1 e2e3 e2c5", "Virhe siirrossa", false);
    }
    
    @Test
    public void torninLaillisetSiirrotOikein() {
        testaaSiirrot("a2a4 h7h5 a1a3 h8h6 a3h3 h6a6 h3h5 a6a4 h5g5 a4a1 g5g4 a1b1", "Virhe siirrossa", true);
    }
    
    @Test
    public void torninVirheelisetSiirrotEstetty() {
        teeSiirrot("a2a4 h7h5 a1a3 h8h6 a3f3 h5h4");
        testaaSiirrot("f3f2 f3f8 f3g2 f3e2 f3g4 f3e4", "Virhe siirrossa", false);
    }
    
    @Test
    public void ratsunLaillisetSiirrotOikein() {
        testaaSiirrot("b1a3 b8a6 a3c4 a6c5 c4d6 c5d3 d6f7 d3b2 f7g5 b2d1 g5e6 d1e3 e6c7 e3f5", "Virhe siirrossa", true);
    }
    
    @Test
    public void ratsunVirheelisetSiirrotEstetty() {
        teeSiirrot("b1a3 b8a6 a3c4 a6c5");
        testaaSiirrot("c4b2 c4c2 c4c6 c4d4", "Virhe siirrossa", false);
    }
    
    @Test
    public void testaaKuninkaanKaataminenPaattaaPelin() {
        teeSiirrot("b1a3 b8a6 a3c4 a6c5 c4d6 c5d3 d6f7 d3b2 f7g5 b2d1 g5e6 d1e3 e6c7 e3f5");
        assertTrue("Virhe siirrossa c7e8", testattava.siirto("c7e8"));
        assertTrue("Pelin päättyminen ei toiminut", testattava.loppu());
    }
    
    @Test
    public void syotyNappulaLisataanListalle() {
        teeSiirrot("a2a4 b7b5");
        Ruutu syoty = testattava.ruutu("b5");
        teeSiirrot("a4b5");
        assertEquals("", 1, testattava.syodyt.size());
        assertEquals("", syoty, testattava.syodyt.get(0));
    }
    
    @Test
    public void peliTallentuuHistoriaOtukseen() {
        teeSiirrot("a2a4 b7b5 a4a5");
        assertEquals("", 3, historia.getSiirrot().size());
        assertEquals("", "a2a4", historia.getSiirrot().get(0));
        assertEquals("", "b7b5", historia.getSiirrot().get(1));
        assertEquals("", "a4a5", historia.getSiirrot().get(2));
    }
      
    
// APUMETODIT
    
    HashMap<Ruutu, int[]> paikat = new HashMap<>();
    private void sijainnit() {
        upseerit(Vari.VALKOINEN, 0);
        soltut(Vari.VALKOINEN, 1);
        upseerit(Vari.MUSTA, 7);
        soltut(Vari.MUSTA, 6);
        tyhjat();
    }

    private void upseerit(Vari vari, int rivi) {
        paikat.put(new Torni(vari, 0, 0), new int[]{rivi,0,rivi,7});
        paikat.put(new Ratsu(vari, 0, 0), new int[]{rivi,1,rivi,6});
        paikat.put(new Lahetti(vari, 0, 0), new int[]{rivi,2,rivi,5});
        paikat.put(new Kuningatar(vari, 0, 0), new int[]{rivi,3});
        paikat.put(new Kuningas(vari, 0, 0), new int[]{rivi,4});
    }
    
    private void soltut(Vari vari, int rivi) {
        paikat.put(new Sotilas(vari, 0, 0), new int[]{rivi,0,rivi,1,rivi,2,rivi,3,rivi,4,rivi,5,rivi,6,rivi,7});
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
    
    private void teeSiirrot(String mj) {
        String[] siirrot = mj.split(" ");
        for (String s : siirrot) {
            testattava.siirto(s);
        }
    }
    
    private void testaaSiirrot(String mj, String viesti, Boolean b) {
        String[] siirrot = mj.split(" ");
        for (String s : siirrot) {
            if (b) {
                lailisenTarkistus(s, viesti);
            } else {
                virheelisenTarkistus(s, viesti);
            }
        }
    }
    
    private void lailisenTarkistus(String s, String viesti) {
        assertTrue(viesti+" "+s, testattava.siirto(s));
    }

    private void virheelisenTarkistus(String s, String viesti) {
        assertFalse(viesti+" "+s, testattava.siirto(s));
    }

}
