package jshakki.jshakki;

import jshakki.kayttoliittyma.Kayttoliittyma;
import jshakki.kayttoliittyma.TekstiKaytto;
import jshakki.logiikka.ShakkiPeli;

/**
 * 
 * 
 */
public class App 
{
    public static void main( String[] args )
    {
        ShakkiPeli peli = new ShakkiPeli();
        TekstiKaytto liittyma  = new TekstiKaytto(peli);
        if (args.length == 0) {
            liittyma = new TekstiKaytto(peli);
        }
        
        liittyma.run();
       
        
        
       
    }
}
