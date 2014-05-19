

package jshakki.jshakki;

import jshakki.domain.Kuningas;
import jshakki.domain.Nappula;
import jshakki.domain.Tilanne;
import jshakki.domain.Vari;

/**
 *
 * @author termanty
 */
public class RunGame {
    int lev = 8;
    int kor = 8;
    
    
    public void run() {
        System.out.println("toimii");
        pelitilanteenAlustus();
    }
    
    private void pelitilanteenAlustus() {
//        Tilanne sijainnit = new Tilanne();
        Nappula[][] tilanne = new Nappula[kor][lev];
        tilanne[0][4] = new Kuningas(Vari.VALKOINEN);
        tilanne[7][4] = new Kuningas(Vari.MUSTA);
        
        System.out.println("");
        for (int i = 0; i < kor; i++) {
            for (int j = 0; j < lev; j++) {
                if (tilanne[i][j] == null) {
                    System.out.print(".");
                } else {
                    System.out.print(merkki(tilanne[i][j]));
                }
            }
            System.out.println("");
        }
        
    }
    
    private char merkki(Nappula n) {
        if (n.vari() == Vari.VALKOINEN) {
            return n.nimi();
        } else {
            return (char)(n.nimi() - 32);
        }
    } 
    
}
