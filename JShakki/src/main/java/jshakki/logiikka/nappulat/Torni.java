
package jshakki.logiikka.nappulat;

import java.util.ArrayList;
import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Liike;
import jshakki.logiikka.liikkeet.Vaaka;

/**
 *
 * @author termanty
 */
public class Torni implements Ruutu {
    private final char nimi = 't';
    private final Vari vari;
    private final ArrayList<Liike> liikkeet;

    public Torni (Vari vari) {
        this.vari = vari;
        liikkeet = new ArrayList<>();
        liikkeet.addAll(Arrays.asList(Vaaka.values()));
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
