

package jshakki.jshakki;

import jshakki.logiikka.Logiikka;

/**
 *
 * @author termanty
 */
public class JShakkirunko {
    public Logiikka logiikka;
    public Pelihistoria historia;
    private HistoriatiedostonKasittelija tiedostonkasittelija;
    

    public JShakkirunko() {
        tiedostonkasittelija = new HistoriatiedostonKasittelija();
        historia = new Pelihistoria();
        uusiPeli();
    }

    public void uusiPeli() {
        this.logiikka = new Logiikka(historia);
    }
    
    public void lataaTallennettuPeli() {
        
    }
    
    public void tallennaPeli() {
        
    }
}
