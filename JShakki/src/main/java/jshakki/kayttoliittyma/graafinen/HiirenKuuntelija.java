

package jshakki.kayttoliittyma.graafinen;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import jshakki.logiikka.Shakkipeli;
import jshakki.logiikka.nappulat.Ruutu;

/**
 *
 * @author termanty
 */
public class HiirenKuuntelija implements MouseListener, MouseMotionListener {
    Shakkipeli peli;
    Piirtoalusta piirtoalusta;
    Ruutu mista;
    int korMis;
    int levMis;
    Ruutu minne;

    public HiirenKuuntelija(Shakkipeli peli, Piirtoalusta piirtoalusta) {
        this.peli = peli;
        this.piirtoalusta = piirtoalusta;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        piirtoalusta.x = p.x;
        piirtoalusta.y = p.y - 30;
        piirtoalusta.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        piirtoalusta.xKor = (p.x - 40) / 50;
        piirtoalusta.yKor = (p.y - 40 - 30) / 50;
        if (mikaNappula(p.y, p.x, mista)) {
            korMis = (p.y - 40 - 30)/50;
            levMis = (p.x - 40)/50;
        }
        piirtoalusta.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point p = e.getPoint();
        if (mikaNappula(p.y, p.x, minne)) {
            int kor = (p.y - 40 - 30) / 50;
            int lev = (p.x - 40) / 50;
            if (peli.siirto(7-korMis, levMis, 7-kor, lev)) {
                for (Iterator<Gnappula> it = piirtoalusta.nappulat.iterator(); it.hasNext();) {
                    Gnappula g = it.next();
                    if (g.kor == kor && g.lev == lev) {
                        it.remove();
                    }
                }
                for (Gnappula g : piirtoalusta.nappulat) {
                    if (g.kor == korMis && g.lev == levMis) {
                        g.kor = kor;
                        g.lev = lev;
                    }
                }
                
            }
        }
        piirtoalusta.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) { 
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    private boolean mikaNappula(int y, int x, Ruutu r) {
        x = (x - 40) / 50;
        y = (y - 40 - 30) / 50;
        
        if (!(y < 0 || y > 7 || x < 0 || x > 7)) {
            r = peli.ruutu(y, x);
            return true;
        }
        return false;
    }
    
    
    
}
