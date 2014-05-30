

package jshakki.kayttoliittyma.graafinen;

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
        if (pelilaudalla(p)) {
            korMis = ykoordinaatti(p.y);
            levMis = xkoordinaatti(p.x);
            if (!peli.tyhjaRuutu(7-korMis, levMis) &&
                    peli.vuoro().equals(peli.ruutu(7 - korMis, levMis).vari().name())) {
                piirtoalusta.korosta = true;
                piirtoalusta.yKorostus = korMis;
                piirtoalusta.xKorostus = levMis;
                piirtoalusta.siirrot = peli.sallitutLiikkeet(7 - korMis, levMis);
                piirtoalusta.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point p = e.getPoint();
        if (pelilaudalla(p)) {
            int kor = ykoordinaatti(p.y);
            int lev = xkoordinaatti(p.x);
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
        piirtoalusta.korosta = false;
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
    
    private boolean pelilaudalla(Point p) {
        return !(p.y - 70 < 0 || p.y - 70 > 400 || p.x - 40 < 0 || p.x - 40  > 400);
    }
    
    private int ykoordinaatti(int y) {
        return (y - 40 - 30) / 50;
    }
    
    private int xkoordinaatti(int x) {
        return (x - 40) / 50;
    }
    
    
    
    
}
