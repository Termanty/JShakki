package jshakki.logiikka.nappulat;

import jshakki.logiikka.liikkeet.Askel;
import java.util.Arrays;
import jshakki.logiikka.Vari;

/**
 * 
 * @author termanty
 */
public class Kuningas extends Nappula {

    public Kuningas(Vari vari) {
        super(vari, 'k');
        liikkeet.addAll(Arrays.asList(Askel.values()));
    }
    
}
