
package jshakki.jshakki;

import jshakki.logiikka.Logiikka;
import jshakki.logiikka.nappulat.Vari;

/**
 * JShakkirunko luokka huolehtii ohjelman toiminnan eri vaiheista.
 * Peli aloitus ja lopetus. Tekoälyn vuorot.
 */
public class JShakkirunko {

    /**
     * Pelinappuloiden siirroista huolehtiva luokka.
     */
    public Logiikka logiikka = null;

    /**
     * Tämä muutuja pitää kirjaa tehdyistä siirroista.
     */
    public Pelihistoria historia = null; 

    /**
     * Ollaanko peliä edeltävässä aloitusnäyttö tilassa.
     */
    public boolean aloitustila = true;

    /**
     * Onko tekoalylla siirtovuoro.
     */
    public boolean tekoalynVuoro = false;
    
    /**
     * Valkoisia nappuloita ohjaava tekoaly.
     */
    private Tekoaly valkoinen = null;
    
    /**
     * Mustia nappuloita ohjaava tekoaly.
     */
    private Tekoaly musta = null;

    /**
     * Konstruktori jossa siirretään peli aloitustilaan.
     */
    public JShakkirunko() {
        aloitustila();
    }

    /**
     * Uuden pelin aloittaminen.
     */
    public void uusiPeli() {
        aloitustila = false;
        if (valkoinen != null) {
            tekoalySiirto(valkoinen);
        }
    }
    
    /**
     * Käynnissä olevan pelin lopettaminen.
     * Siirretään peli aloitustilaan.
     */
    public void lopetaPeli() {
        aloitustila();
    }
    
    /**
     * Siirron toteutus ja tekoälyn siirto.
     * @param korMista mitä siirretään korkeus.
     * @param levMista mitä siirretään leveys.
     * @param korMinnne minne siirretään korkeus.
     * @param levMinne minne siirretään leveys.
     * @return true jos siirto onnistui.
     */
    public boolean siirto(int korMista, int levMista, int korMinnne, int levMinne) {
        if (logiikka.siirto(korMista, levMista, korMinnne, levMinne)) {
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
    
    // gettereitä ja settereitä

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
    
    /**
     * Tekoaly suorittaa oman siirtonsa.
     * @param vari on tekoalyn pelaama vari.
     */
    private void tekoalySiirto(Tekoaly vari) {
        tekoalynVuoro = true;
        vari.laskeSiirto();
        tekoalynVuoro = false;
    }
    
    /**
     * Tämä metodi vaihtaa pelin aloitustilaan.
     */
    private void aloitustila() {
        aloitustila = true;
        historia = new Pelihistoria();
        logiikka = new Logiikka(historia);
        valkoinen = null;
        musta = null;
    }
}
