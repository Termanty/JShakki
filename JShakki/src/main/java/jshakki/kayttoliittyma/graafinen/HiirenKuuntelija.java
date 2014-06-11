
package jshakki.kayttoliittyma.graafinen;

import jshakki.kayttoliittyma.graafinen.elementit.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import jshakki.jshakki.JShakkirunko;
import jshakki.jshakki.OS;

/**
 * Hiirenkuuntelija luokka seuraa hiiren toimintoja.
 * Pelin nappuloiden siirtäminen tapahtuu painamalla hiiren nappi pohjaan ja vapauttamalla
 * se halutun ruudun yläpuolella. Hiiren olessa eri elementien yläpuolella niitä
 * korostetaan tarvittaessa.
 */
public class HiirenKuuntelija implements MouseListener, MouseMotionListener {
    private final JShakkirunko peli;
    private final Piirtoalusta piirtoalusta;
    private int korMis;
    private int levMis;
    
    public HiirenKuuntelija(JShakkirunko peli, Piirtoalusta piirtoalusta) {
        this.peli = peli;
        this.piirtoalusta = piirtoalusta;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Vaihtaja.hiiriPaalla(e.getPoint())) {
            piirtoalusta.teema.vaihdaTeema();
        }
        if (peli.aloitustila) {
            aloitusElementtiaKlikattu(e.getPoint());
        } else {
            elementtiaKlikattu(e.getPoint());
        }
        piirtoalusta.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!peli.aloitustila) {
            nappulanJaSiirtojenKorostus(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!peli.aloitustila) {
            siirraNappula(e.getPoint());
            RuudunKorostaja.korosta = false;
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
        elementtienTunnistus(e.getPoint());
    }
    
    private void elementtienTunnistus(Point p) {
        Vaihtaja.korosta = Vaihtaja.hiiriPaalla(p);
        Oikeudet.korosta = Oikeudet.hiiriPaalla(p);
        if (peli.aloitustila) {
            PelinAloittaja.korosta = PelinAloittaja.hiiriPaalla(p);
            LataaVanha.korosta = LataaVanha.hiiriPaalla(p);
            ValkoisenValinta.korosta = ValkoisenValinta.hiiriPaalla(p);
            MustanValinta.korosta = MustanValinta.hiiriPaalla(p);        
        } else {
            Ylakolmio.korosta = Ylakolmio.hiiriPaalla(p);
            Alakolmio.korosta = Alakolmio.hiiriPaalla(p);
            Slider.korosta = Slider.hiiriPaalla(p);
            Tallenna.korosta = Tallenna.hiiriPaalla(p);
            Lopeta.korosta = Lopeta.hiiriPaalla(p);
        }
        piirtoalusta.repaint();
    }

    private void elementtiaKlikattu(Point p) {
        if (Ylakolmio.hiiriPaalla(p)) {
            Slider.kasvata();
        }
        if (Alakolmio.hiiriPaalla(p)) {
            Slider.vahenna();
        }
        if (Lopeta.hiiriPaalla(p)) {
            peli.lopetaPeli();
        }
    }
    
    private void aloitusElementtiaKlikattu(Point p) {
        if (PelinAloittaja.hiiriPaalla(p)) {
            peli.uusiPeli();
        }
        if (MustanValinta.hiiriPaalla(p)) {
            peli.setMusta();
            MustanValinta.ihminenValittu = peli.getMusta() == null;
        }
        if (ValkoisenValinta.hiiriPaalla(p)) {
            peli.setValkoinen();
            ValkoisenValinta.ihminenValittu = peli.getValkoinen() == null;
        }
    }
    
    private void nappulanJaSiirtojenKorostus(Point p) {
        if (Pelilauta.hiiriPaalla(p)) {
            korMis = ykoordinaatti(p.y);
            levMis = xkoordinaatti(p.x);
            if (!peli.logiikka.tyhjaRuutu(7-korMis, levMis) &&
                peli.logiikka.vuoro().equals(peli.logiikka.ruutu(7 - korMis, levMis).vari().name())) {
                RuudunKorostaja.korosta = true;
                RuudunKorostaja.y = korMis;
                RuudunKorostaja.x = levMis;
                RuudunKorostaja.siirrot = peli.logiikka.sallitutLiikkeet(7 - korMis, levMis);
                piirtoalusta.repaint();
            }
        }
    }
    
    private void siirraNappula(Point p) {
        if (!peli.logiikka.loppu() && Pelilauta.hiiriPaalla(p)) {
            int kor = ykoordinaatti(p.y);
            int lev = xkoordinaatti(p.x);
            peli.siirto(7-korMis, levMis, 7-kor, lev);
        }
    }
    
    private int xkoordinaatti(int x) {
        return (x - 300 + OS.X) / 50;
    }
    
    private int ykoordinaatti(int y) {
        return (y - 40 + OS.Y) / 50;
    }
 
    
}
