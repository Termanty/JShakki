
package jshakki.kayttoliittyma.graafinen.teema;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Lataa luokka lukee kuva tiedostoja image kansiosta.
 */
public class Lataa {

    /**
     * kuva metodi lukee kuvan tiedostosta.
     * @param fileName muutujassa on avattavan tiedoston nimi.
     * @return palautetaan kuva BufferImage muodossa.
     */
    public BufferedImage kuva(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.err.println("Tiedostoa " + fileName + " ei löydetty.");
        }
        return img;
    }
}
