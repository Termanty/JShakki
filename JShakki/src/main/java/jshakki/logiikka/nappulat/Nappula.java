

package jshakki.logiikka.nappulat;

import java.util.ArrayList;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Liike;

/**
 *
 * @author termanty
 */
public class Nappula implements Ruutu {
    private final char nimi;
    private final Vari vari;
    final ArrayList<Liike> liikkeet;
    private int liikeLaskuri;

    public Nappula(Vari vari, char nimi) {
        this.vari = vari;
        this.nimi = nimi;
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
    public ArrayList<Liike> liikkeet() {
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
}
