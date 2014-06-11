
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
    public final Vari vari;
    private Logiikka logiikka;
    
    public boolean vuoro = false;

    public Tekoaly(Vari vari, Logiikka logiikka) {
        this.vari = vari;
        this.logiikka = logiikka;
    }

    void laskeSiirto() {
        vuoro = true;
        List<Ruutu> nappulat = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!logiikka.tyhjaRuutu(i, j) && logiikka.ruutu(i, j).vari() == vari) {
                    nappulat.add(logiikka.ruutu(i, j));
                }
            }
        }
        List<int[]> siirrot;
        Ruutu nappula;
        do {
            int arvottuRuutu = (int) (Math.random() * nappulat.size());
            nappula = nappulat.get(arvottuRuutu);
            siirrot = logiikka.sallitutLiikkeet(nappula.kor(), nappula.lev());
        } while (siirrot.isEmpty());
        int arvottuSiirto = (int) (Math.random() * siirrot.size());
        int[] siirto = siirrot.get(arvottuSiirto);
        if (!logiikka.loppu() && logiikka.siirto(nappula.kor(), nappula.lev(), siirto[0], siirto[1])) {
            System.err.println("tekoaly siirto toimi");
        } else {
            System.err.println("tekoaly siirto sucked");
        }
        vuoro = false;
    }
    
    
    
}
