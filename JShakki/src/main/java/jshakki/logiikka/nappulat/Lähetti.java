
package jshakki.logiikka.nappulat;

import java.util.ArrayList;
import java.util.Arrays;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Liike;
import jshakki.logiikka.liikkeet.Vino;

/**
 *
 * @author termanty
 */
public class Lähetti implements Ruutu {
    private final char nimi = 'l';
    private final Vari vari;
    private final ArrayList<Liike> liikkeet;

    public Lähetti (Vari vari) {
        this.vari = vari;
        liikkeet = new ArrayList<>();
        liikkeet.addAll(Arrays.asList(Vino.values()));
    }

    @Override
    public char nimi() {
        return nimi;
    }

    @Override
    public Vari vari() {
        return vari;
    }

    @Override
    public ArrayList<Liike> liikkeet() {
        return liikkeet;
    }
    
}
