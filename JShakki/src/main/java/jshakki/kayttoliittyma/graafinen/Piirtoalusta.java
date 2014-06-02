package jshakki.kayttoliittyma.graafinen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import jshakki.logiikka.Shakkipeli;
import jshakki.logiikka.Vari;
import jshakki.logiikka.nappulat.Nappula;
import jshakki.logiikka.nappulat.Ruutu;

/**
 *
 * @author termanty
 */
public class Piirtoalusta extends JPanel {
    private Shakkipeli peli;
    private Lataa hae = new Lataa();
    public List<Gnappula> nappulat;
    public List<Gnappula> syodytNappulat;
    public List<int[]> siirrot = null;
    public int x;
    public int y;
    public int yKorostus;
    public int xKorostus;
    public boolean korostaRuutu = false;
    
 
    public Piirtoalusta(Shakkipeli peli) {
        super();
        super.setBackground(Color.WHITE);
        this.peli = peli;
        this.nappulat = new ArrayList<>();
        this.syodytNappulat = new ArrayList<>();
        lisaaPeliMerkit();
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(hae.kuva(Teema.tausta), 0, 0, null);
        g.drawImage(hae.kuva(Teema.shakkilauta), 280, 20, null);
        g.setColor(Teema.vaaleaPohja);
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
        for (Gnappula n : nappulat) {
            grafiikka.drawImage(n.img, 300+(n.lev*50), 40+(n.kor*50), null);
        }
    }
    
    private void piirraMahdolliset(Graphics grafiikka) {
        if (korostaRuutu) {
            grafiikka.setColor(Color.green);
            grafiikka.drawRect(300+(xKorostus*50), 40+(yKorostus*50), 50, 50);
            for (int[] s : siirrot) {
                grafiikka.setColor(Teema.ruudunKorostus);
                grafiikka.fillRect(300+((s[1])*50), 40+((7-s[0])*50), 50, 50);
            }
        }
    }

    private void lisaaPeliMerkit() {
        for (int kor = 0; kor < 8; kor++) {
            for (int lev = 0; lev < 8; lev++) {
                Ruutu n = peli.ruutu(kor, lev);
                if (n.vari() == null) {
                    continue;
                }
                String nimi = ""+n.nimi();
                if (n.vari() == Vari.VALKOINEN) {
                    nimi = "White "+nimi+".png";
                } else {
                    nimi = "Black "+nimi+".png";
                }
                nimi = Teema.nappulat+nimi;
                nappulat.add(new Gnappula(n, hae.kuva(nimi), 7-kor, lev, n.vari()));
            }
        }
    }

    

    private void syodytNappulat(Graphics g) {
        int vPaikka = 0;
        int mPaikka = 0;
        for (Gnappula n : syodytNappulat) {
            if (n.vari == Vari.VALKOINEN) {
                g.drawImage(n.img, 745+(vPaikka%4*50), 390-(vPaikka/4*50), null);
                vPaikka++;
            } else {
                g.drawImage(n.img, 745+(mPaikka%4*50), 40+(mPaikka/4*50), null);
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
            piirraElemetti(g, "VALKOINEN", Teema.tummaTeksti, Teema.vaaleaPohja, 50);
        } else {
            piirraElemetti(g, "MUSTA", Teema.vaaleaTeksti, Teema.tummaPohja, 85);
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
        if (Teema.korosta) {
            g.setColor(Teema.korostettuVaaleaPohja);
            g.fillRect(Teema.X, Teema.Y, Teema.LEV, Teema.KOR);
            g.setColor(Teema.tummaTeksti);
            g.drawString("vaihda teema", Teema.X + 20, Teema.Y + 27);
        } else {
            g.setColor(Teema.vaaleaPohja);
            g.fillRect(Teema.X, Teema.Y, Teema.LEV, Teema.KOR);
            g.setColor(Teema.tummaTeksti);
            g.drawString(Teema.nimi, Teema.X + 20, Teema.Y + 27);
        }
        
    }

    private void oikeudet(Graphics g) {
        g.setFont(new Font("Ariel", Font.BOLD, 18));
        if (Oikeudet.korosta) {
            g.setColor(Teema.korostettuVaaleaPohja);
            g.fillRect(Oikeudet.X, Oikeudet.Y, Oikeudet.LEV, Oikeudet.KOR);
            g.setColor(Teema.tummaTeksti);
            g.drawString("Tero Mäntylä 2014", Oikeudet.X + 15, Oikeudet.Y + 27);
        } else {
            g.setColor(Teema.vaaleaPohja);
            g.fillRect(Oikeudet.X, Oikeudet.Y, Oikeudet.LEV, Oikeudet.KOR);
            g.setColor(Teema.tummaTeksti);      
            g.drawString("copyright", Oikeudet.X + 20, Oikeudet.Y + 27);
        }

    }

    
    
    

    
}

