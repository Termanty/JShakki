
package jshakki.kayttoliittyma.graafinen;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import jshakki.logiikka.Shakkipeli;

/**
 * Luokka GraafinenKayttoliittyma maarittelee GUI perustoiminnallisuudet.
 */
public class GraafinenKayttoliittyma implements Runnable {
    private JFrame frame;
    Piirtoalusta piirtoalusta;
    BufferedImage img;
    final private Shakkipeli peli;

    /**
     * Konstruktori luokalle.
     * @param peli sisältää pelilogiikan.
     */
    public GraafinenKayttoliittyma(Shakkipeli peli) {
        this.peli = peli;     
    }

    /**
     * Tässä metodissa on perusinformaatio luotavasta ikkunasta.
     */
    @Override
    public void run() {
        frame = new JFrame("JShakki");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());
        lisaaKuuntelijat();

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta(peli);
        container.add(piirtoalusta);
    }
    
    private void lisaaKuuntelijat() {
        frame.addMouseListener(new HiirenKuuntelija(peli, piirtoalusta));
        frame.addMouseMotionListener(new HiirenKuuntelija(peli, piirtoalusta));
    }

    public JFrame getFrame() {
        return frame;
    }
}
