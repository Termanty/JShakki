package jshakki.jshakki;

import javax.swing.SwingUtilities;
import jshakki.kayttoliittyma.graafinen.GraafinenKayttoliittyma;

/**
 * App luokka toimii shakkipeli ohjelman käynnistäjänä.
 */
public class App 
{
    public static void main( String[] args )
    {
        GraafinenKayttoliittyma gui = new GraafinenKayttoliittyma(new JShakkirunko());
        SwingUtilities.invokeLater(gui);
    }
}
