package jshakki.kayttoliittyma.graafinen;

import jshakki.kayttoliittyma.graafinen.elementit.Oikeudet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import jshakki.logiikka.Shakkipeli;
import jshakki.logiikka.Vari;
import jshakki.logiikka.nappulat.Ruutu;

/**
 *
 * @author termanty
 */
public class Piirtoalusta extends JPanel {
    private final Shakkipeli peli;
    private final Lataa hae;
    public final Teema teema;
    public List<Gnappula> nappulat;
    public List<Gnappula> syodytNappulat;
    public List<int[]> siirrot = null;
    public int x;
    public int y;
    public int yKorostus;
    public int xKorostus;
    public boolean korostaRuutu = false;
    
    public BufferedImage tausta;
    public BufferedImage lauta;
    
 
    public Piirtoalusta(Shakkipeli peli) {
        super();
        super.setBackground(Color.WHITE);
        
        this.peli = peli;
        this.hae = new Lataa();
        this.teema = new Teema();
        this.nappulat = new ArrayList<>();
        this.syodytNappulat = new ArrayList<>();
        alustaPelimerkit();
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        teema();
        g.drawImage(tausta, 0, 0, null);
        g.drawImage(lauta, 280, 20, null);
        g.setColor(teema.vaaleaPohja);
        g.fillRect(740, 25, 220, 430);
        sijoitaNappulatLaudalle(g);
        syodytNappulat(g);
        peliVuoro(g);
        piirraMahdolliset(g);
//        grafiikka.setColor(Color.red);
//        grafiikka.fillOval(x, y, 10, 10);
        aktiiviElementit(g);
    }

    private void sijoitaNappulatLaudalle(Graphics grafiikka) {   
        for (Gnappula gn : nappulat) {
            grafiikka.drawImage(gn.img, 300+(gn.lev*50), 40+(gn.kor*50), null);
        }
    }
    
    private void piirraMahdolliset(Graphics grafiikka) {
        if (korostaRuutu) {
            grafiikka.setColor(Color.green);
            grafiikka.drawRect(300+(xKorostus*50), 40+(yKorostus*50), 50, 50);
            for (int[] s : siirrot) {
                grafiikka.setColor(teema.ruudunKorostus);
                grafiikka.fillRect(300+((s[1])*50), 40+((7-s[0])*50), 50, 50);
            }
        }
    }

    private void teema() {
        if (teema.vaihdettu) {
            tausta = hae.kuva(teema.tausta);
            lauta = hae.kuva(teema.shakkilauta);
            for (Gnappula gn : nappulat) {
                gn.img = hae.kuva(teema.nappulat + gn.vari + gn.nimi() + ".png");
            }
            for (Gnappula gn : syodytNappulat) {
                gn.img = hae.kuva(teema.nappulat+gn.vari+gn.nimi()+".png");
            }
            teema.vaihdettu = false;
        }
    }

    private void syodytNappulat(Graphics g) {
        int vPaikka = 0;
        int mPaikka = 0;
        for (Gnappula gn : syodytNappulat) {
            if (gn.vari.equals("White")) {
                g.drawImage(gn.img, 745+(vPaikka%4*50), 390-(vPaikka/4*50), null);
                vPaikka++;
            } else {
                g.drawImage(gn.img, 745+(mPaikka%4*50), 40+(mPaikka/4*50), null);
                mPaikka++;
            }
        }
    }
    
    private void aktiiviElementit(Graphics g) {
        peliVuoro(g);
        teemanVaihtaja(g);
        oikeudet(g);
        
    }
    
    private void peliVuoro(Graphics g) {
        g.setFont(new Font("Ariel", Font.BOLD, 28));
        if (peli.vuoro().equals(Vari.VALKOINEN.name())) {
            piirraElemetti(g, "VALKOINEN", teema.tummaTeksti, teema.vaaleaPohja, 50);
        } else {
            piirraElemetti(g, "MUSTA", teema.vaaleaTeksti, teema.tummaPohja, 85);
        }
    }
    
    private void piirraElemetti(Graphics g, String teksti, Color tekstinVari, Color taustanVari, int xTeksti) {
        g.setColor(taustanVari);
        g.fillRect(25, 25, 235, 65);
        g.setColor(tekstinVari);
        g.drawString(teksti, xTeksti, 70);
    }
    
    private void teemanVaihtaja(Graphics g) {
        g.setFont(new Font("Ariel", Font.BOLD, 20));
        if (Vaihtaja.korosta) {
            g.setColor(teema.korostettuVaaleaPohja);
            g.fillRect(Vaihtaja.X, Vaihtaja.Y, Vaihtaja.LEV, Vaihtaja.KOR);
            g.setColor(teema.tummaTeksti);
            g.drawString("vaihda teema", Vaihtaja.X + 20, Vaihtaja.Y + 27);
        } else {
            g.setColor(teema.vaaleaPohja);
            g.fillRect(Vaihtaja.X, Vaihtaja.Y, Vaihtaja.LEV, Vaihtaja.KOR);
            g.setColor(teema.tummaTeksti);
            g.drawString(teema.nimi, Vaihtaja.X + 20, Vaihtaja.Y + 27);
        }
        
    }

    private void oikeudet(Graphics g) {
        g.setFont(new Font("Ariel", Font.BOLD, 18));
        if (Oikeudet.korosta) {
            g.setColor(teema.korostettuVaaleaPohja);
            g.fillRect(Oikeudet.X, Oikeudet.Y, Oikeudet.LEV, Oikeudet.KOR);
            g.setColor(teema.tummaTeksti);
            g.drawString("Tero Mäntylä 2014", Oikeudet.X + 15, Oikeudet.Y + 27);
        } else {
            g.setColor(teema.vaaleaPohja);
            g.fillRect(Oikeudet.X, Oikeudet.Y, Oikeudet.LEV, Oikeudet.KOR);
            g.setColor(teema.tummaTeksti);      
            g.drawString("copyright", Oikeudet.X + 20, Oikeudet.Y + 27);
        }

    }

    private void alustaPelimerkit() {
        for (int kor = 0; kor < 8; kor++) {
            for (int lev = 0; lev < 8; lev++) {
                Ruutu nappula = peli.ruutu(kor, lev);
                if (nappula.vari() != null) {
                    String vari;
                    if (nappula.vari() == Vari.VALKOINEN) {
                        vari = "White";
                    } else {
                        vari = "Black";
                    }
                    nappulat.add(new Gnappula(nappula, 7 - kor, lev, vari));
                }
            }
        }
    }

    
    
    

    
}

