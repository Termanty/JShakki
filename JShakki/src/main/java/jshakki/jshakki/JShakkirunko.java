

package jshakki.jshakki;

import jshakki.logiikka.Logiikka;

/**
 *
 * @author termanty
 */
public class JShakkirunko {
    public Logiikka logiikka = null;
    public Pelihistoria historia = null;
    private HistoriatiedostonKasittelija tiedostonkasittelija;
    
    public boolean aloitustila = true;
    

    public JShakkirunko() {
        tiedostonkasittelija = new HistoriatiedostonKasittelija();
//        uusiPeli();
    }

    public void uusiPeli() {
        historia = new Pelihistoria();
        logiikka = new Logiikka(historia);
        aloitustila = false;
    }
    
    public void lopetaPeli() {
        aloitustila = true;
    }
    
    public void lataaTallennettuPeli() {
        
    }
    
    public void tallennaPeli() {
        
    }
}
