
package jshakki.logiikka.nappulat;

import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Vino;

/**
 * Lähetti shakkinappula.
 */
public class Lahetti extends Nappula {

    public Lahetti (Vari vari, int kor, int lev) {
        super(vari, 'l', kor, lev);
        liikkeet.addAll(Arrays.asList(Vino.values()));
    }
}
