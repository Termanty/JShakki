
package jshakki.kayttoliittyma.teksti;

import jshakki.logiikka.Vari;
import jshakki.logiikka.nappulat.Ruutu;

/**
 * Muuta luokka vaihtaa shakkinappulan nimen kuvaksi.
 */
public class Muuta {
    
    /**
     * Tässä muutetaan shakkinappulan nimi kuvaksi.
     * @param nappula on shakkinappula
     * @return palauttaa ascii kuvan shakkinappulasta.
     */
    public static char kuvaksi(Ruutu nappula) {
        switch (merkki(nappula)) {
            case 's':
                return '♙';
            case 'S':
                return '♟';
            case 't':
                return '♖';
            case 'T':
                return '♜';
            case 'r':
                return '♘';
            case 'R':
                return '♞';
            case 'l':
                return '♗';
            case 'L':
                return '♝';
            case 'q':
                return '♕';
            case 'Q':
                return '♛';
            case 'k':
                return '♔';
            case 'K':
                return '♚';     
        }
        return '…';
    }
    
    private static char merkki(Ruutu n) {
        if (n.vari() == Vari.VALKOINEN) {
            return n.nimi();
        } else {
            return (char)(n.nimi() - 32);
        }
    }
}
