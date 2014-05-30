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
    public int x;
    public int y;
    public int yKor;
    public int xKor;
    
 
    public Piirtoalusta(Shakkipeli peli) {
        super();
        super.setBackground(Color.WHITE);
        this.peli = peli;
        this.nappulat = new ArrayList<>();
        lisaaPeliMerkit();
        
    }
 
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        
        graphics.drawImage(hae.kuva("Board black.jpg"), 20, 20, null);
        sijoitaNappulatLaudalle(graphics);
        graphics.setColor(Color.green);
        graphics.drawRect(40+(xKor*50), 40+(yKor*50), 50, 50);
        graphics.setColor(Color.red);
        graphics.fillOval(x, y, 10, 10);
    }

    private void sijoitaNappulatLaudalle(Graphics graphics) {
        for (Gnappula n : nappulat) {
            graphics.drawImage(n.img, 40+(n.lev*50), 40+(n.kor*50), null);
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

