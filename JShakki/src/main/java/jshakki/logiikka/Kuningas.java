package jshakki.logiikka;

import jshakki.logiikka.liikkeet.Askel;
import jshakki.logiikka.liikkeet.Liike;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author termanty
 */
public class Kuningas implements Nappula{
    private final char nimi = 'k';
    private final Vari vari;
    private ArrayList<Liike> liikkeet;

    public Kuningas(Vari vari) {
        this.vari = vari;
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
