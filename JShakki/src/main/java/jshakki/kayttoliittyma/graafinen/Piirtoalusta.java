package jshakki.kayttoliittyma.graafinen;

import java.awt.Color;
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
        this.korosta = false;
        lisaaPeliMerkit();
    }
 
    @Override
    protected void paintComponent(Graphics grafiikka) {
        super.paintComponent(grafiikka);
        
        
        grafiikka.drawImage(hae.kuva("Board black.jpg"), 20, 20, null);
        sijoitaNappulatLaudalle(grafiikka);
        piirraMahdolliset(grafiikka);
        grafiikka.setColor(Color.red);
        grafiikka.fillOval(x, y, 10, 10);
    }

    private void sijoitaNappulatLaudalle(Graphics grafiikka) {   
        for (Gnappula n : nappulat) {
            grafiikka.drawImage(n.img, 40+(n.lev*50), 40+(n.kor*50), null);
        }
    }
    
    private void piirraMahdolliset(Graphics grafiikka) {
        if (korosta) {
            grafiikka.setColor(Color.green);
            grafiikka.drawRect(40+(xKorostus*50), 40+(yKorostus*50), 50, 50);
            for (int[] s : siirrot) {
                grafiikka.setColor(new Color(0,255,0,70));
                grafiikka.fillRect(40+((s[1])*50), 40+((7-s[0])*50), 50, 50);
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
                nappulat.add(new Gnappula(n, hae.kuva(nimi), 7-kor, lev));
            }
        }
    }
}

