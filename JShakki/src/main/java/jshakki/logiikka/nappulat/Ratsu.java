
package jshakki.logiikka.nappulat;

import java.util.Arrays;
import jshakki.logiikka.liikkeet.Hyppy;

/**
 * Ratsu shakkinappula.
 */
public class Ratsu extends Nappula {

    public Ratsu (Vari vari, int kor, int lev) {
        super(vari, 'r', kor, lev);
        liikkeet.addAll(Arrays.asList(Hyppy.values()));
    }
}
