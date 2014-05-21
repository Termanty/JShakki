
package jshakki.kayttoliittyma;

import jshakki.logiikka.Vari;
import jshakki.logiikka.nappulat.Ruutu;

/**
 *
 * @author termanty
 */
public class Muuta {
    
    public static char kuvaksi(Ruutu n) {
        switch (merkki(n)) {
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
