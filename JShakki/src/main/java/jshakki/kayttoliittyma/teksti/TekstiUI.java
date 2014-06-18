
package jshakki.kayttoliittyma.teksti;

import java.util.Scanner;
import jshakki.logiikka.Logiikka;
import jshakki.logiikka.nappulat.Ruutu;
import jshakki.logiikka.nappulat.Vari;

/**
 * TÄMÄ LUOKKA EI OLE KÄYTÖSSÄ LOPULLISESSA OHJELMASSA!
 * TekstiUI on teksti pohjainen käyttöliittymä JShakki-peliin.
 * Tätä luokkaa on käyttetty ainoastaan ohjelman kehityksen alkuvaiheessa
 * pelilogiikan koodaamiseen.
 */
public class TekstiUI implements Runnable {
    private final Logiikka PELI;
    Scanner lukija;
    
    public TekstiUI(Logiikka uusi) {
        lukija = new Scanner(System.in);
        this.PELI = uusi;
    }
    
    public void tulostaPelitilanne() {
        System.out.println("\nvuoroNro: "+PELI.vuoroNro());
        System.out.println("pelaaja: "+PELI.vuoro());
        System.out.println("  ╔═══════════════╗");
        for (int i = 7; i >= 0; i--) {
            System.out.print((i+1) + " ║");
            for (int j = 0; j < 8; j++) {
                System.out.print(Muuta.kuvaksi(PELI.ruutu(i,j)) + (j<7 ? " " : "║\n"));
            }
        }
        System.out.println("  ╚═══════════════╝");
        System.out.println("   a b c d e f g h\n");
    }
    
    private char merkki(Ruutu n) {
        if (n.vari() == Vari.VALKOINEN) {
            return n.nimi();
        } else {
            return (char)(n.nimi() - 32);
        }
    } 

    @Override
    public void run() {
        System.out.println("UUSI PELI\n");   
        while (!PELI.loppu()) {
            tulostaPelitilanne();
            System.out.print("Anna siirto: ");
            String siirto = lukija.nextLine().trim().toLowerCase();
            if (!(syotteenTarkistus(siirto) && PELI.siirto(siirto))) {
                System.out.println("Virheellinen syöte. Anna siirto muodossa a2a3");
            }
        }
        tulostaPelitilanne();
        System.out.println("PELI PÄÄTTYI");
    }

    private boolean syotteenTarkistus(String siirto) {
        return siirto.length() == 4 && 
                siirto.charAt(0) >= 'a' && siirto.charAt(0) <= 'h' && 
                siirto.charAt(2) >= 'a' && siirto.charAt(2) <= 'h' &&
                siirto.charAt(1) >= '1' && siirto.charAt(1) <= '8' &&
                siirto.charAt(3) >= '1' && siirto.charAt(3) <= '8';
    }
}
