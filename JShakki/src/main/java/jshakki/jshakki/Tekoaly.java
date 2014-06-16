
package jshakki.jshakki;

import java.util.ArrayList;
import java.util.List;
import jshakki.logiikka.Logiikka;
import jshakki.logiikka.nappulat.Ruutu;
import jshakki.logiikka.nappulat.Vari;

/**
 * Tekoaly luokka tekee siirrot tietokone pelaajalle.
 */
public class Tekoaly {

    /**
     * Tekoälyn pelaaman nappuloiden väri.
     */
    public final Vari vari;
    
    /**
     * Logiikka huolehtii pelin siirroista.
     */
    private final Logiikka logiikka;

    /**
     * Konstruktori
     * @param vari jota tekoäly ohjaa.
     * @param logiikka nappuloiden paikkojen ja liikkeiden selvittämiseen.
     */
    public Tekoaly(Vari vari, Logiikka logiikka) {
        this.vari = vari;
        this.logiikka = logiikka;
    }

    /**
     * Tässä etsitään tekoäly siirto.
     * Ensin arvotaan siirettä nappula. Valitaan vain nappula jota voidaan
     * siirtää. Tämän jälkeen arvotaan nappulan siirtymispaikka.
     */
    public void laskeSiirto() {
        List<Ruutu> nappulat = haeSiirrettavat();
        Ruutu nappula;
        List<int[]> siirrot;
        do {
            nappula = nappulat.get((int) (Math.random() * nappulat.size()));
            siirrot = logiikka.sallitutLiikkeet(nappula.kor(), nappula.lev());
        } while (siirrot.isEmpty());
        int[] siirto = siirrot.get((int) (Math.random() * siirrot.size()));
        logiikka.siirto(nappula.kor(), nappula.lev(), siirto[0], siirto[1]);
    }
    
    /**
     * Etsitään nappulat joita tekoäly voi siirtää.
     * @return lista siirrettävistä nappuloista.
     */
    private List<Ruutu> haeSiirrettavat() {
        List<Ruutu> nappulat = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!logiikka.tyhjaRuutu(i, j) && logiikka.ruutu(i, j).vari() == vari) {
                    nappulat.add(logiikka.ruutu(i, j));
                }
            }
        }
        return nappulat;
    }
}
