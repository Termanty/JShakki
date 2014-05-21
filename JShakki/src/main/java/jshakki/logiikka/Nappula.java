package jshakki.logiikka;

import jshakki.logiikka.liikkeet.Liike;
import java.util.ArrayList;

/**
 *
 * @author termanty
 */
public interface Nappula {
    
    char nimi();
    Vari vari();
    ArrayList<Liike> liikkeet();
}
