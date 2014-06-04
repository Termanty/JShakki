
package jshakki.logiikka.nappulat;

import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Vaaka;

/**
 * Torni shakkinappula.
 */
public class Torni extends Nappula {

    public Torni (Vari vari, int kor, int lev) {
        super(vari, 't', kor, lev);
        liikkeet.addAll(Arrays.asList(Vaaka.values()));
    }
}
