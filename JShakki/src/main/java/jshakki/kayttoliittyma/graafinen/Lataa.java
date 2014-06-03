
package jshakki.kayttoliittyma.graafinen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author termanty
 */
public class Lataa {
    
    public BufferedImage kuva(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.err.println(fileName);
        }
        return img;
    }
}
