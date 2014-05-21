
package jshakki.logiikka.nappulat;

import java.util.ArrayList;
import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Liike;
import jshakki.logiikka.liikkeet.Hyppy;

/**
 *
 * @author termanty
 */
public class Ratsu implements Ruutu {
    private final char nimi = 'r';
    private final Vari vari;
    private final ArrayList<Liike> liikkeet;

    public Ratsu (Vari vari) {
        this.vari = vari; 
        liikkeet = new ArrayList<>();
        liikkeet.addAll(Arrays.asList(Hyppy.values()));
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
