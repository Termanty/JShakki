
package jshakki.kayttoliittyma.graafinen;

import jshakki.kayttoliittyma.graafinen.elementit.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import jshakki.jshakki.OS;
import jshakki.logiikka.Shakkipeli;

/**
 * Hiirenkuuntelija luokka seuraa hiiren toimintoja.
 * Pelin nappuloiden siirtäminen tapahtuu painamalla hiiren nappi pohjaan ja vapauttamalla
 * se halutun ruudun yläpuolella. Hiiren olessa eri elementien yläpuolella niitä
 * korostetaan tarvittaessa.
 */
public class HiirenKuuntelija implements MouseListener, MouseMotionListener {
    private final Shakkipeli peli;
    private final Piirtoalusta piirtoalusta;
    private int korMis;
    private int levMis;
    
    public HiirenKuuntelija(Shakkipeli peli, Piirtoalusta piirtoalusta) {
        this.peli = peli;
        this.piirtoalusta = piirtoalusta;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        elementtiaKlikattu(e.getPoint());
        piirtoalusta.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        nappulanJaSiirtojenKorostus(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        siirraNappula(e.getPoint());
        RuudunKorostaja.korosta = false;
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
    
    private void elementtienTunnistus(Point p) {
        Vaihtaja.korosta = Vaihtaja.hiiriPaalla(p);
        Oikeudet.korosta = Oikeudet.hiiriPaalla(p);
        piirtoalusta.repaint();
    }

    private void elementtiaKlikattu(Point p) {
        if (Vaihtaja.hiiriPaalla(p)) {
            piirtoalusta.teema.vaihdaTeema();
        }
    }
    
    private void nappulanJaSiirtojenKorostus(Point p) {
        if (Pelilauta.hiiriPaalla(p)) {
            korMis = ykoordinaatti(p.y);
            levMis = xkoordinaatti(p.x);
            if (!peli.tyhjaRuutu(7-korMis, levMis) &&
                    peli.vuoro().equals(peli.ruutu(7 - korMis, levMis).vari().name())) {
                RuudunKorostaja.korosta = true;
                RuudunKorostaja.y = korMis;
                RuudunKorostaja.x = levMis;
                RuudunKorostaja.siirrot = peli.sallitutLiikkeet(7 - korMis, levMis);
                piirtoalusta.repaint();
            }
        }
    }
    
    private void siirraNappula(Point p) {
        if (Pelilauta.hiiriPaalla(p)) {
            int kor = ykoordinaatti(p.y);
            int lev = xkoordinaatti(p.x);
            if (peli.siirto(7-korMis, levMis, 7-kor, lev)) {
                for (Iterator<NappulanKuva> it = piirtoalusta.nappulat.iterator(); it.hasNext();) {
                    NappulanKuva g = it.next();
                    if (g.kor == kor && g.lev == lev) {
                        piirtoalusta.syodytNappulat.add(g);
                        it.remove();
                    }
                }
                for (NappulanKuva g : piirtoalusta.nappulat) {
                    if (g.kor == korMis && g.lev == levMis) {
                        g.kor = kor;
                        g.lev = lev;
                    }
                }
            }
        }
    }
    
    private int xkoordinaatti(int x) {
        return (x - 300 + OS.X) / 50;
    }
    
    private int ykoordinaatti(int y) {
        return (y - 40 + OS.Y) / 50;
    }
 
    
}
