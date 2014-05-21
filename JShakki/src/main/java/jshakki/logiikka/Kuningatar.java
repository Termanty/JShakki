
package jshakki.logiikka;

import java.util.ArrayList;
import java.util.Arrays;
import jshakki.logiikka.liikkeet.Liike;
import jshakki.logiikka.liikkeet.Vaaka;
import jshakki.logiikka.liikkeet.Vino;


/**
 *
 * @author termanty
 */
public class Kuningatar implements Nappula {
    private final char nimi = 'q';
    private final Vari vari;
    private ArrayList<Liike> liikkeet;

    public Kuningatar (Vari vari) {
        this.vari = vari;      
        liikkeet.addAll(Arrays.asList(Vaaka.values()));
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
