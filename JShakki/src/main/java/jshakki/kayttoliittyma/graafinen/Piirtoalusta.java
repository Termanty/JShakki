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
        vaihdaNappulanKuvat(nappulat);
        vaihdaNappulanKuvat(syodytNappulat);
    }
    
    private void piirraElementit(Graphics g) {
        Taustakuva.piirra(g);
        Pelilauta.piirra(g);   
        Vaihtaja.piirra(g, teema);
        Oikeudet.piirra(g, teema);
        //
        if (peli.aloitustila) {
//            AloitusPohja.piirra(g, teema, hae);
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
            Vuoro.piirra(g, teema, peli.logiikka);
            SiirrotPohja.piirra(g, teema);
            Siirrot.piirra(g, teema, peli.historia);
            Alakolmio.piirra(g, teema);
            Ylakolmio.piirra(g, teema);
            Slider.piirra(g, teema, peli.historia);
            Tallenna.piirra(g, teema);
            Lopeta.piirra(g, teema);
            piirraNappulat(g);
            piirraSyodytNappulat(g);
        }
    }

    private void piirraNappulat(Graphics g) {
        if (peli.logiikka.paivita()) {
            nappulat.clear();
            alustaPelimerkit();
            vaihdaKuvat();
            peli.logiikka.kuittaa();
        }
        for (NappulanKuva nappula : nappulat) {
            nappula.piirra(g);
        }
        if (!peli.logiikka.loppu()) {
            RuudunKorostaja.piirra(g, teema);
        } else {
            PeliLoppu.piirra(g, teema);
        }
    }

    private void piirraSyodytNappulat(Graphics g) {
        SyodytPohja.piirra(g, teema);
        int vPaikka = 0;
        int mPaikka = 0;
        if (syodytNappulat.size() != peli.logiikka.syodyt.size()) {
            alustaSyodyt();
        }
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

    private void alustaSyodyt() {
        syodytNappulat.clear();
        for (Ruutu nappula : peli.logiikka.syodyt) {
            String vari = nappula.vari() == Vari.VALKOINEN ? "White" : "Black";
            syodytNappulat.add(new NappulanKuva(nappula, vari));
        }
        vaihdaNappulanKuvat(syodytNappulat);
    }
    
    private void vaihdaNappulanKuvat(List<NappulanKuva> list) {
        for (NappulanKuva nappula : list) {
            nappula.img = hae.kuva(teema.nappulat + nappula.vari + nappula.nimi() + ".png");
        }
    }
    
    
}

