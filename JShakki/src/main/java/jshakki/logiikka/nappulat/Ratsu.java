
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
public class Ratsu extends Nappula {

    public Ratsu (Vari vari) {
        super(vari, 'r');
        liikkeet.addAll(Arrays.asList(Hyppy.values()));
    }
}
