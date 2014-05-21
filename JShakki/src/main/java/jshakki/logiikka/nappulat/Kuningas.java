package jshakki.logiikka.nappulat;

import jshakki.logiikka.nappulat.Ruutu;
import jshakki.logiikka.liikkeet.Askel;
import jshakki.logiikka.liikkeet.Liike;
import java.util.ArrayList;
import java.util.Arrays;
import jshakki.logiikka.Vari;

/**
 * 
 * @author termanty
 */
public class Kuningas implements Ruutu{
    private final char nimi = 'k';
    private final Vari vari;
    private final ArrayList<Liike> liikkeet;

    public Kuningas(Vari vari) {
        this.vari = vari;
        liikkeet = new ArrayList<>();
        liikkeet.addAll(Arrays.asList(Askel.values()));
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
