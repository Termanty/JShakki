
package jshakki.jshakki;

import jshakki.logiikka.Logiikka;
import jshakki.logiikka.nappulat.Vari;

/**
 * JShakkirunko luokka huolehtii ohjelman toiminnan eri vaiheista.
 * Peli aloitus ja lopetus. Tekoälyn vuorot.
 */
public class JShakkirunko {
    public Logiikka logiikka = null;
    public Pelihistoria historia = null;
    private HistoriatiedostonKasittelija tiedostonkasittelija;
    
    public boolean aloitustila = true;
    
    private Tekoaly valkoinen = null;
    private Tekoaly musta = null;

    public JShakkirunko() {
        tiedostonkasittelija = new HistoriatiedostonKasittelija();
        aloitustila();
    }

    public void uusiPeli() {
        if (valkoinen != null) {
            if (musta != null) {
                System.err.println("Ei toimi vielä");
            }
            valkoinen.laskeSiirto();
        }
        aloitustila = false;
    }
    
    public void lopetaPeli() {
        aloitustila();
    }
    
    private void aloitustila() {
        aloitustila = true;
        historia = new Pelihistoria();
        logiikka = new Logiikka(historia);
    }
    
    public boolean siirto(int kor, int lev, int korMin, int levMin) {
        if (!logiikka.loppu() && logiikka.siirto(kor, lev, korMin, levMin)) {
            if (musta != null && logiikka.vuoro().equals(musta.vari.name())) {
                musta.laskeSiirto();
            }
            if (valkoinen != null && logiikka.vuoro().equals(valkoinen.vari.name())) {
                valkoinen.laskeSiirto();
            }
            return true;
        }
        return false;
    }
    
    public void lataaTallennettuPeli() {
        
    }
    
    public void tallennaPeli() {
        
    }

    public Tekoaly getValkoinen() {
        return valkoinen;
    }


    public void setValkoinen() {
        if (valkoinen == null) {
            valkoinen = new Tekoaly(Vari.VALKOINEN, logiikka);
        } else {
            valkoinen = null;
        }
    }
    
    public Tekoaly getMusta() {
        return musta;
    }


    public void setMusta() {
        if (musta == null) {
            musta = new Tekoaly(Vari.MUSTA, logiikka);
        } else {
            musta = null;
        }
    }

}
