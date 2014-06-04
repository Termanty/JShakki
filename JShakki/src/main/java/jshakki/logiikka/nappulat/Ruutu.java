package jshakki.logiikka.nappulat;

import jshakki.logiikka.liikkeet.Liikesuunta;
import java.util.ArrayList;
import jshakki.logiikka.Vari;

/**
 * Interface ruudussa oleville erilaisille nappuloille ja tyhjalle ruudulle.
 */
public interface Ruutu {
    
    char nimi();
    Vari vari();
    ArrayList<Liikesuunta> liikkeet();
    int siirtojenMaara();
    void kasvataSiirtoLaskuria();
    boolean vastustaja(Ruutu r);
}
