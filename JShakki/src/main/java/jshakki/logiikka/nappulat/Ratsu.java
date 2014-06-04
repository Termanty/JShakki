
package jshakki.logiikka.nappulat;

import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Hyppy;

/**
 * Ratsu shakkinappula.
 */
public class Ratsu extends Nappula {

    public Ratsu (Vari vari) {
        super(vari, 'r');
        liikkeet.addAll(Arrays.asList(Hyppy.values()));
    }
}
