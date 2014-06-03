

package jshakki.kayttoliittyma.graafinen;

import jshakki.kayttoliittyma.graafinen.elementit.Oikeudet;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import jshakki.jshakki.OSnimi;
import jshakki.logiikka.Shakkipeli;

/**
 *
 * @author termanty
 */
public class HiirenKuuntelija implements MouseListener, MouseMotionListener {
    private final int Y_SOVITA = OSnimi.onLinux() ? 0 : -30;
    private final int X_SOVITA = OSnimi.onLinux() ? 0 : -10;
    private Shakkipeli peli;
    private Piirtoalusta piirtoalusta;
    private int korMis;
    private int levMis;
    

    public HiirenKuuntelija(Shakkipeli peli, Piirtoalusta piirtoalusta) {
        this.peli = peli;
        this.piirtoalusta = piirtoalusta;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        elementtiaKlikattu(p);
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
                piirtoalusta.korostaRuutu = true;
                piirtoalusta.yKorostus = korMis;
                piirtoalusta.xKorostus = levMis;
                piirtoalusta.siirrot = peli.sallitutLiikkeet(7 - korMis, levMis);
                piirtoalusta.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        siirraNappula(e.getPoint());
        piirtoalusta.korostaRuutu = false;
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
        elementtienTunnistus(e.getPoint());
    }
    
    private boolean pelilaudalla(Point p) {
        return p.x >= 300 && p.x <= 700 && p.y + Y_SOVITA >= 40 && p.y + Y_SOVITA <= 440;
    }
    
    private int ykoordinaatti(int y) {
        return (y - 40 + Y_SOVITA) / 50;
    }
    
    private int xkoordinaatti(int x) {
        return (x - 300) / 50;
    }

    private void elementtienTunnistus(Point p) {
        Vaihtaja.korosta = Vaihtaja.hiiriPaalla(p, X_SOVITA, Y_SOVITA);
        Oikeudet.korosta = Oikeudet.hiiriPaalla(p, X_SOVITA, Y_SOVITA);
        piirtoalusta.repaint();
    }

    private void elementtiaKlikattu(Point p) {
        if (Vaihtaja.hiiriPaalla(p, X_SOVITA, Y_SOVITA)) {
            piirtoalusta.teema.vaihdaTeema();
        }
    }
    
    private void siirraNappula(Point p) {
        if (pelilaudalla(p)) {
            int kor = ykoordinaatti(p.y);
            int lev = xkoordinaatti(p.x);
            if (peli.siirto(7-korMis, levMis, 7-kor, lev)) {
                for (Iterator<Gnappula> it = piirtoalusta.nappulat.iterator(); it.hasNext();) {
                    Gnappula g = it.next();
                    if (g.kor == kor && g.lev == lev) {
                        piirtoalusta.syodytNappulat.add(g);
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
    }
    
}
