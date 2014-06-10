package jshakki.kayttoliittyma.graafinen;

import jshakki.logiikka.nappulat.Vari;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import jshakki.jshakki.JShakkirunko;
import jshakki.kayttoliittyma.graafinen.elementit.*;
import jshakki.kayttoliittyma.graafinen.teema.*;
import jshakki.logiikka.nappulat.Ruutu;

/**
 * Piirtoalusta luokka toimii pohjana koko shakkipelin piirtamiselle.
 */
public class Piirtoalusta extends JPanel {
    private final JShakkirunko peli;
    private final Lataa hae;
    public final Teema teema;
    public List<NappulanKuva> nappulat;
    public List<NappulanKuva> syodytNappulat;
    
    private boolean nappulatAlustettu = true;
 
    public Piirtoalusta(JShakkirunko peli) {
        super();
        this.peli = peli;
        this.hae = new Lataa();
        this.teema = new Teema();
        this.nappulat = new ArrayList<>();
        this.syodytNappulat = new ArrayList<>();
        teema.alustaTeema();
//        alustaPelimerkit();
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        teema();
        piirraElementit(g);
    }
    
    private void teema() {
        if (teema.vaihdettu) {
            vaihdaKuvat();
            teema.vaihdettu = false;
        }
    }
    
    private void vaihdaKuvat() {
        Taustakuva.img = hae.kuva(teema.tausta);
        Pelilauta.img = hae.kuva(teema.shakkilauta);
        for (NappulanKuva nappula : nappulat) {
            nappula.img = hae.kuva(teema.nappulat + nappula.vari + nappula.nimi() + ".png");
        }
        for (NappulanKuva nappula : syodytNappulat) {
            nappula.img = hae.kuva(teema.nappulat + nappula.vari + nappula.nimi() + ".png");
        }
    }
    
    private void piirraElementit(Graphics g) {
        Taustakuva.piirra(g);
        Pelilauta.piirra(g);
        Vuoro.piirra(g, teema, peli.logiikka);
        Vaihtaja.piirra(g, teema);
        Oikeudet.piirra(g, teema);
        SiirrotPohja.piirra(g, teema);
        Siirrot.piirra(g, teema, peli.historia);
        Alakolmio.piirra(g, teema);
        Ylakolmio.piirra(g, teema);
        Slider.piirra(g, teema, peli.historia);
        Tallenna.piirra(g, teema);
        Lopeta.piirra(g, teema);
        
        //
        if (peli.aloitustila) {
            AloitusPohja.piirra(g, teema, hae);
            PelinAloittaja.piirra(g, teema);
            LataaVanha.piirra(g, teema);
//            Pelaajat.piirra(g, teema);
            ValkoisenValinta.piirra(g, teema);
            MustanValinta.piirra(g, teema);
            nappulatAlustettu = false;
        } else {
            if (!nappulatAlustettu) {
                nappulat.clear();
                syodytNappulat.clear();
                alustaPelimerkit();
                vaihdaKuvat();
                teema.alustaTeema();
            }
            piirraNappulat(g);
            piirraSyodytNappulat(g);
        }
    }

    private void piirraNappulat(Graphics g) {
        if (peli.logiikka.sotilasKorotettu()) {
            nappulat.clear();
            alustaPelimerkit();
            vaihdaKuvat();
            peli.logiikka.kuittaaSotilaanKorotus();
        }
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
                Ruutu nappula = peli.logiikka.ruutu(kor, lev);
                if (nappula.vari() != null) {
                    String vari = nappula.vari() == Vari.VALKOINEN ? "White" : "Black";
                    nappulat.add(new NappulanKuva(nappula, vari));
                }
            }
        }
        nappulatAlustettu = true;
    }
}

