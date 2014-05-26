

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

    public Nappula(Vari vari, char nimi) {
        this.vari = vari;
        this.nimi = nimi;
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
}
