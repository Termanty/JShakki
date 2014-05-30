package jshakki.jshakki;

import javax.swing.SwingUtilities;
import jshakki.kayttoliittyma.Kayttoliittyma;
import jshakki.kayttoliittyma.Tekstikaytto;
import jshakki.kayttoliittyma.graafinen.GraafinenKayttoliittyma;
import jshakki.logiikka.Shakkipeli;

/**
 * 
 * 
 */
public class App 
{
    public static void main( String[] args )
    {
        Shakkipeli peli = new Shakkipeli();
        Tekstikaytto liittyma  = new Tekstikaytto(peli);
        if (args.length == 0) {
            liittyma = new Tekstikaytto(peli);
        }
        
//        liittyma.run();
        
        GraafinenKayttoliittyma liit = new GraafinenKayttoliittyma(peli);
        SwingUtilities.invokeLater(liit);

       
    }
}
