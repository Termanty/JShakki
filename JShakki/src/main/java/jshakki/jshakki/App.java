package jshakki.jshakki;

import jshakki.kayttoliittyma.Kayttoliittyma;
import jshakki.kayttoliittyma.Tekstikaytto;
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
        
        liittyma.run();
       
        
        
       
    }
}
