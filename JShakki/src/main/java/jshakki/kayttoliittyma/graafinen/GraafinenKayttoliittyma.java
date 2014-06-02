
package jshakki.kayttoliittyma.graafinen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import jshakki.logiikka.Shakkipeli;

/**
 *
 * @author termanty
 */
public class GraafinenKayttoliittyma implements Runnable {
    private JFrame frame;
    Piirtoalusta piirtoalusta;
    BufferedImage img;
    private Shakkipeli peli;

    public GraafinenKayttoliittyma(Shakkipeli peli) {
        this.peli = peli;     
    }

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
