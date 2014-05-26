
package jshakki.logiikka.nappulat;

import java.util.ArrayList;
import jshakki.logiikka.Vari;
import jshakki.logiikka.liikkeet.Liike;

/**
 *
 * @author termanty
 */
public class Tyhja implements Ruutu {
    private final char nimi = 'x';

    @Override
    public char nimi() {
        return nimi;
    }

    @Override
    public Vari vari() {
        return null;
    }

    @Override
    public ArrayList<Liike> liikkeet() {
        return null;
    }

    @Override
    public int siirtojenMaara() {
        return -1;
    }

    @Override
    public void kasvataSiirtoLaskuria() {
    }

    @Override
    public boolean vastustaja(Ruutu r) {
        return false;
    }
    
}
