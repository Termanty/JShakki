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
    Shakkipeli peli;
    Lataa hae = new Lataa();
    public List<Gnappula> nappulat;
    public List<Gnappula> syodytNappulat;
    public List<int[]> siirrot = null;
    public int x;
    public int y;
    public int yKorostus;
    public int xKorostus;
    public boolean korosta;
    
 
    public Piirtoalusta(Shakkipeli peli) {
        super();
        super.setBackground(Color.WHITE);
        this.peli = peli;
        this.nappulat = new ArrayList<>();
        this.syodytNappulat = new ArrayList<>();
        this.korosta = false;
        lisaaPeliMerkit();
    }
 
    @Override
    protected void paintComponent(Graphics grafiikka) {
        super.paintComponent(grafiikka);
        
        grafiikka.drawImage(hae.kuva("Black background.jpg"), 0, 0, null);
        grafiikka.drawImage(hae.kuva("Board black.jpg"), 280, 20, null);
        grafiikka.setColor(new Color(255,255,255,80));
        grafiikka.fillRect(740, 25, 220, 430);
        sijoitaNappulatLaudalle(grafiikka);
        syodytNappulat(grafiikka);
        vuoro(grafiikka);
        piirraMahdolliset(grafiikka);
//        grafiikka.setColor(Color.red);
//        grafiikka.fillOval(x, y, 10, 10);
    }

    private void sijoitaNappulatLaudalle(Graphics grafiikka) {   
        for (Gnappula n : nappulat) {
            grafiikka.drawImage(n.img, 300+(n.lev*50), 40+(n.kor*50), null);
        }
    }
    
    private void piirraMahdolliset(Graphics grafiikka) {
        if (korosta) {
            grafiikka.setColor(Color.green);
            grafiikka.drawRect(300+(xKorostus*50), 40+(yKorostus*50), 50, 50);
            for (int[] s : siirrot) {
                grafiikka.setColor(new Color(0,255,0,30));
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
                nappulat.add(new Gnappula(n, hae.kuva(nimi), 7-kor, lev, n.vari()));
            }
        }
    }

    private void vuoro(Graphics grafiikka) {
        grafiikka.setFont(new Font("Ariel", Font.BOLD, 28));
        if (peli.vuoro().equals(Vari.VALKOINEN.name())) {
            grafiikka.setColor(new Color(255,255,255,200));
            grafiikka.fillRect(25, 25, 235, 65);
            grafiikka.setColor(new Color(0,0,0,200));
            grafiikka.setFont(null);
            grafiikka.drawString("VALKOINEN", 55, 50);
        } else {
            grafiikka.setColor(new Color(0,0,0,200));
            grafiikka.fillRect(25, 25, 235, 65);
            grafiikka.setColor(new Color(255,255,255,200));
            grafiikka.drawString("MUSTA", 85, 50);
        }
    }

    private void syodytNappulat(Graphics grafiikka) {
        int vPaikka = 0;
        int mPaikka = 0;
        for (Gnappula n : syodytNappulat) {
            if (n.vari == Vari.VALKOINEN) {
                grafiikka.drawImage(n.img, 745+(vPaikka%4*50), 390-(vPaikka/4*50), null);
                vPaikka++;
            } else {
                grafiikka.drawImage(n.img, 745+(mPaikka%4*50), 40+(mPaikka/4*50), null);
                mPaikka++;
            }
        }
    }
}

