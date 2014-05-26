package jshakki.logiikka.nappulat;

import jshakki.logiikka.liikkeet.Liike;
import java.util.ArrayList;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Siirto;

/**
 *
 * @author termanty
 */
public interface Ruutu {
    
    char nimi();
    Vari vari();
    ArrayList<Liike> liikkeet();
    int siirtojenMaara();
    void kasvataSiirtoLaskuria();
    boolean vastustaja(Ruutu r);
}
