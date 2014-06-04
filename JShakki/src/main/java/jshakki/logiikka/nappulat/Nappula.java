
package jshakki.logiikka.nappulat;

import java.util.ArrayList;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Liikesuunta;

/**
 * Nappula luokka on yliluokka shakkinappuloille.
 */
public class Nappula implements Ruutu {
    private final char nimi;
    private final Vari vari;
    
    public int kor;
    public int lev;
    
    final ArrayList<Liikesuunta> liikkeet;
    private int liikeLaskuri;

    public Nappula(Vari vari, char nimi, int kor, int lev) {
        this.nimi = nimi;
        this.vari = vari;
        this.kor = kor;
        this.lev = lev;
        this.liikeLaskuri = 0;
        liikkeet = new ArrayList<>();
    }

    @Override
    public char nimi() {
        return nimi;
    }

    @Override
    public Vari vari() {
        return vari;
    }

    @Override
    public ArrayList<Liikesuunta> liikkeet() {
        return liikkeet;
    }    

    @Override
    public int siirtojenMaara() {
        return liikeLaskuri;
    }

    @Override
    public void kasvataSiirtoLaskuria() {
        liikeLaskuri++;
    }

    @Override
    public boolean vastustaja(Ruutu r) {
        if (this.vari == Vari.VALKOINEN) {
            if (r.vari() == Vari.MUSTA) return true;
        } else {
            if (r.vari() == Vari.VALKOINEN) return true;
        }
        return false;
    }

    @Override
    public void uusiSijainti(int kor, int lev) {
        this.kor = kor;
        this.lev = lev;
    }

    @Override
    public int kor() {
        return this.kor;
    }

    @Override
    public int lev() {
        return this.lev;
    }
}
