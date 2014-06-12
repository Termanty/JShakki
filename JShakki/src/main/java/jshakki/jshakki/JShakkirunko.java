
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
    public boolean aloitustila = true;
    public boolean tekoalynVuoro = false;
    private Tekoaly valkoinen = null;
    private Tekoaly musta = null;

    public JShakkirunko() {
        aloitustila();
    }

    public void uusiPeli() {
        aloitustila = false;
        if (valkoinen != null) {
            tekoalySiirto(valkoinen);
        }
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
                tekoalySiirto(musta);
            }
            if (valkoinen != null && logiikka.vuoro().equals(valkoinen.vari.name())) {
                tekoalySiirto(valkoinen);
            }
            return true;
        }
        return false;
    }

    public Tekoaly getValkoinen() {
        return valkoinen;
    }

    public void setValkoinen() {
        if (valkoinen == null && musta == null) {
            valkoinen = new Tekoaly(Vari.VALKOINEN, logiikka);
        } else {
            valkoinen = null;
        }
    }
    
    public Tekoaly getMusta() {
        return musta;
    }


    public void setMusta() {
        if (musta == null && valkoinen == null) {
            musta = new Tekoaly(Vari.MUSTA, logiikka);
        } else {
            musta = null;
        }
    }

    private void tekoalySiirto(Tekoaly vari) {
        tekoalynVuoro = true;
        vari.laskeSiirto();
        tekoalynVuoro = false;
    }
}
