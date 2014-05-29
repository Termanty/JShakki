
package jshakki.logiikka.nappulat;

import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.MustaSotilas;
import jshakki.logiikka.liikkeet.ValkeaSotilas;

/**
 *
 * @author termanty
 */
public class Sotilas extends Nappula {

    public Sotilas (Vari vari) {
        super(vari, 's');
        if (vari == Vari.VALKOINEN) {
            liikkeet.addAll(Arrays.asList(ValkeaSotilas.values()));
        } else {
            liikkeet.addAll(Arrays.asList(MustaSotilas.values()));
        }
    }

}
