package jshakki.kayttoliittyma.graafinen;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import jshakki.kayttoliittyma.graafinen.elementit.*;
import jshakki.kayttoliittyma.graafinen.teema.*;
import jshakki.logiikka.*;
import jshakki.logiikka.nappulat.Ruutu;

/**
 * Piirtoalusta luokka toimii pohjana koko shakkipelin piirtamiselle.
 */
public class Piirtoalusta extends JPanel {
    private final Shakkipeli peli;
    private final Lataa hae;
    public final Teema teema;
    public List<NappulanKuva> nappulat;
    public List<NappulanKuva> syodytNappulat; 
 
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
        piirraElementit(g);
    }
    
    private void teema() {
        if (teema.vaihdettu) {
            Taustakuva.img = hae.kuva(teema.tausta);
            Pelilauta.img = hae.kuva(teema.shakkilauta);
            for (NappulanKuva nappula : nappulat) {
                nappula.img = hae.kuva(teema.nappulat + nappula.vari + nappula.nimi() + ".png");
            }
            for (NappulanKuva nappula : syodytNappulat) {
                nappula.img = hae.kuva(teema.nappulat + nappula.vari + nappula.nimi() + ".png");
            }
            teema.vaihdettu = false;
        }
    }
    
    private void piirraElementit(Graphics g) {
        Taustakuva.piirra(g);
        Pelilauta.piirra(g);
        piirraNappulat(g);
        piirraSyodytNappulat(g);
        Vuoro.piirra(g, teema, peli);
        Vaihtaja.piirra(g, teema);
        Oikeudet.piirra(g, teema);
    }

    private void piirraNappulat(Graphics g) {   
        for (NappulanKuva nappula : nappulat) {
            nappula.piirra(g);
        }
        RuudunKorostaja.piirra(g, teema);
    }

    private void piirraSyodytNappulat(Graphics g) {
        SyodytPohja.piirra(g, teema);
        int vPaikka = 0;
        int mPaikka = 0;
        for (NappulanKuva nappula : syodytNappulat) {
            if (nappula.vari.equals("White")) {
                nappula.piirra(g, 745+(vPaikka%4*50), 390-(vPaikka/4*50));
                vPaikka++;
            } else {
                nappula.piirra(g, 745+(mPaikka%4*50), 40+(mPaikka/4*50));
                mPaikka++;
            }
        }
    }
    
    private void alustaPelimerkit() {
        for (int kor = 0; kor < 8; kor++) {
            for (int lev = 0; lev < 8; lev++) {
                Ruutu nappula = peli.ruutu(kor, lev);
                if (nappula.vari() != null) {
                    String vari = nappula.vari() == Vari.VALKOINEN ? "White" : "Black";
                    nappulat.add(new NappulanKuva(nappula, 7 - kor, lev, vari));
                }
            }
        }
    }
}

