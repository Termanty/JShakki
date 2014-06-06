package jshakki.jshakki;

import javax.swing.SwingUtilities;
import jshakki.kayttoliittyma.teksti.TekstiUI;
import jshakki.kayttoliittyma.graafinen.GraafinenKayttoliittyma;

/**
 * App luokka toimii shakkipeli ohjelman käynnistäjänä.
 */
public class App 
{
    public static void main( String[] args )
    {

        JShakkirunko peli = new JShakkirunko();
//        TekstiUI liittyma  = new TekstiUI(peli);
//        if (args.length == 0) {
//            liittyma = new TekstiUI(peli);
//        }
//        liittyma.run();
        
        GraafinenKayttoliittyma liit = new GraafinenKayttoliittyma(peli);
        SwingUtilities.invokeLater(liit);
    }
}
