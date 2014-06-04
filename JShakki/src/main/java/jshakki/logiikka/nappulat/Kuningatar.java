
package jshakki.logiikka.nappulat;

import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Vaaka;
import jshakki.logiikka.liikkeet.Vino;

/**
 * Kuningatar shakkinappula.
 */
public class Kuningatar extends Nappula {

    public Kuningatar (Vari vari) {
        super(vari, 'q');
        liikkeet.addAll(Arrays.asList(Vaaka.values()));
        liikkeet.addAll(Arrays.asList(Vino.values()));
    }
}
