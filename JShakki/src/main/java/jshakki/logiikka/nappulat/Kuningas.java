package jshakki.logiikka.nappulat;

import jshakki.logiikka.liikkeet.Askel;
import java.util.Arrays;
import jshakki.logiikka.Vari;

/**
 * Kuningas shakkinappula.
 */
public class Kuningas extends Nappula {

    public Kuningas(Vari vari, int kor, int lev) {
        super(vari, 'k', kor, lev);
        liikkeet.addAll(Arrays.asList(Askel.values()));
    }
    
}
