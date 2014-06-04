
package jshakki.logiikka.nappulat;

import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.MustaSotilas;
import jshakki.logiikka.liikkeet.ValkeaSotilas;

/**
 * Sotilas shakkinappula.
 */
public class Sotilas extends Nappula {

    public Sotilas (Vari vari, int kor, int lev) {
        super(vari, 's', kor, lev);
        if (vari == Vari.VALKOINEN) {
            liikkeet.addAll(Arrays.asList(ValkeaSotilas.values()));
        } else {
            liikkeet.addAll(Arrays.asList(MustaSotilas.values()));
        }
    }

}
