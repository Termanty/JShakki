package jshakki.logiikka.nappulat;

import jshakki.logiikka.liikkeet.Liike;
import java.util.ArrayList;
import jshakki.logiikka.Vari;

/**
 *
 * @author termanty
 */
public interface Ruutu {
    
    char nimi();
    Vari vari();
    ArrayList<Liike> liikkeet();
}
