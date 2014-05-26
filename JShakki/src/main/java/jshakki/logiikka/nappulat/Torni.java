
package jshakki.logiikka.nappulat;

import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Vaaka;

/**
 *
 * @author termanty
 */
public class Torni extends Nappula {

    public Torni (Vari vari) {
        super(vari, 't');
        liikkeet.addAll(Arrays.asList(Vaaka.values()));
    }

}
