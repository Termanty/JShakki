
package jshakki.logiikka;

import jshakki.logiikka.nappulat.Vari;
import java.util.ArrayList;
import java.util.List;
import jshakki.jshakki.Pelihistoria;
import jshakki.logiikka.liikkeet.Liikesuunta;
import jshakki.logiikka.nappulat.*;

/**
 * Shakkipeli luokka huolehtii pelin logiikasta.
 */
public class Logiikka {
    
    Pelihistoria historia;
    
    private final Ruutu[][] PELILAUTA;
    private final Ruutu[][] nappula; // sama kuin PELILAUTA. Tarkoituksena selkeyttää koodia jäljempänä. 
    
    private final Tyhja TYHJA;
    private Vari vuoro;
    private int vuoroNro;
    private boolean peliPaattyi;
    private boolean paivita;
    
    public List<Ruutu> syodyt;
    
    public final int LEV = 8;
    public final int KOR = 8;

    /**
     * Konstruktori alustaa tärkeimmät luokkamuuttujat.
     * @param historia otukseen tallennetaan siirrot.
     */
    public Logiikka(Pelihistoria historia) {
        this.historia = historia;
        this.PELILAUTA = new Ruutu[KOR][LEV];
        this.nappula = this.PELILAUTA;
        this.syodyt = new ArrayList<>();
        this.TYHJA = new Tyhja();
        this.vuoro = Vari.VALKOINEN;
        this.vuoroNro = 1;
        this.peliPaattyi = false;
        this.paivita = false;
        pelitilanteenAlustus();
    }
    
    /**
     * Tässä huolehditaan pelivuoron vaihtamisesta.
     * Vuorot vaihtelevat valkoisen ja mustan pelaajan välillä.
     * Muutuja vuoroNro kasvaa vasta kun sekä valkoinen että musta ovat
     * tehneet siirtonsa.
     */
    public void vaihdaVuoro() {
        if (!peliPaattyi) {
            if (this.vuoro == Vari.VALKOINEN) {
                this.vuoro = Vari.MUSTA;
            } else {
                this.vuoro = Vari.VALKOINEN;
                this.vuoroNro++;
            }
        }
    }

    /**
     * Tässä suoritetaan shakkinappulan siirto.
     * @param kor on nappulan nykyinen ruutu korkeussuunnassa.
     * @param lev on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMin on uusi ruutu korkeussuunnassa.
     * @param levMin on uusi ruutu leveyssuunnassa.
     * @return palautaa true jos siirto hyväksyttiin, muulloin false.
     */
    public boolean siirto(int kor, int lev, int korMin, int levMin) {
        Ruutu syotava = nappula[korMin][levMin];
        if (tarkistaSiirto(kor, lev, korMin, levMin)) {
            teeSiirto(kor, lev, korMin, levMin, syotava);
            return true;
        }
        return false;
    }
    
    /**
     * Tässä suoritetaan shakkinappulan siirto.
     * @param mj on tekstinä tehvä siirto. Esim. a2a3.
     * @return palautaa true jos siirto hyväksyttiin, muulloin false.
     */
    public boolean siirto(String mj) {
        return siirto(num(mj,1), kir(mj,0), num(mj,3), kir(mj,2));
    }  

    /**
     * Hakee shakkilaudan tietyssä ruudussa olevan nappulan.
     * Jos ruutu on tyhjä niin palautetaan tyhjä ruutu.
     * @param kor on ruudun sijainti korkeussuunnassa.
     * @param lev on ruudun sijainti leveyssuunnassa.
     * @return palauttaa joko ruudussa olevan Shakkinappulan tai tyhjän ruudun.
     */
    public Ruutu ruutu(int kor, int lev) {
        return PELILAUTA[kor][lev];
    }
    
    /**
     * Hakee shakkilaudan tietyssä ruudussa olevan nappulan.
     * Jos ruutu on tyhjä niin palautetaan tyhjä ruutu.
     * @param mj on ruutu tekstinä. Esim. a2.
     * @return palauttaa joko ruudussa olevan Shakkinappulan tai tyhjän ruudun.
     */
    public Ruutu ruutu(String mj) {
        return ruutu(num(mj,1),kir(mj,0));
    }
    
    public boolean loppu() {
        return this.peliPaattyi;
    }
    
    public String vuoro() {
        return this.vuoro.name();
    }
    
    public int vuoroNro() {
        return this.vuoroNro;
    }
    
    /**
     * Tässä haetaan shakkinappulan kaikki mahdolliset siirot 
     * tämänhetkisessä asetelmassa.
     * @param kor on shakkinappulan sijainti korkeussuunnassa.
     * @param lev on shakkinappulan sijainti leveyssuunnassa.
     * @return palautta listan sallituista liikkeistä.
     */
    public List<int[]> sallitutLiikkeet(int kor, int lev) {
        List<int[]> mahdSiirrot = new ArrayList<>();
        if (nappula[kor][lev].nimi() == 's') {
            sotilaanSiirrot(kor, lev, mahdSiirrot);
        } else {
            upseerienSiirrot(kor, lev, mahdSiirrot);
        }
        return mahdSiirrot;
    }

    /**
     * Tarkistetaan onko tietty ruutu tyhjä.
     * @param kor tarkistettavan ruudun korkeussuunta.
     * @param lev tarkistettavan ruudun leveyssuunta.
     * @return true jos ruutu tyhjä, muulloin false.
     */
    public boolean tyhjaRuutu(int kor, int lev) {
        return PELILAUTA[kor][lev] == TYHJA;
    }
    
    /**
     * Metodi palauttaa tiedon sotilaan muuttumisesta kuningattareksi.
     * @return true jos sotilas on korotettu kuningattareksi, muutoin false;
     */
    public boolean paivita() {
        return paivita;
    }
    
    /**
     * Käyttöliittymä voi kuitata että se on päivittänyt sotilaan muuttumisen.
     */
    public void kuittaa() {
        paivita = false;
    }
    
    
    
    
/// PRIVATE METODIT ******************************************************************
    
    private void teeSiirto(int kor, int lev, int korMin, int levMin, Ruutu syotava) {
        liisaaSyoty(syotava);
        syodaankoKuningas(korMin, levMin);
        nappula[kor][lev].kasvataSiirtoLaskuria();
        vaihdaPaikat(kor, lev, korMin, levMin);
        historia.tallennaSiirto(kor, lev, korMin, levMin);
        vaihdaVuoro();
    }
    
    private void liisaaSyoty(Ruutu syotava) {
        if (syotava != TYHJA) {
            syodyt.add(syotava);
            paivita = true;
        }
    }
    
    private int kir(String mj, int i) {
        return (int) (mj.charAt(i) - 'a');
    }
    
    private int num(String mj, int i) {
        return (int) (mj.charAt(i) - '1');
    }
    
    private void pelitilanteenAlustus() {
        korkeaArvoiset(Vari.VALKOINEN, 0);
        korkeaArvoiset(Vari.MUSTA, 7);
        sotilaat();
        tyhjat();
    }
    
    private void korkeaArvoiset(Vari vari, int rivi) {
        PELILAUTA[rivi][3] = new Kuningatar(vari, rivi, 3);
        PELILAUTA[rivi][4] = new Kuningas(vari, rivi, 4);
        upseerit(vari, rivi, 0, 1);
        upseerit(vari, rivi, 7, -1);
    }
    
    private void upseerit(Vari vari, int rivi, int alku, int suunta) {
        PELILAUTA[rivi][alku] = new Torni(vari, rivi, alku);
        PELILAUTA[rivi][alku+(1*suunta)] = new Ratsu(vari, rivi, alku+(1*suunta));
        PELILAUTA[rivi][alku+(2*suunta)] = new Lahetti(vari, rivi, alku+(2*suunta));
    }
    
    private void sotilaat() {
        for (int i = 0; i < LEV; i++) {
            PELILAUTA[1][i] = new Sotilas(Vari.VALKOINEN, 1, i);
            PELILAUTA[6][i] = new Sotilas(Vari.MUSTA, 6, i);
        }
    }
    
    private void tyhjat() {
        for (int i = 0; i < LEV; i++) {
            for (int j = 2; j < 6; j++) {
                PELILAUTA[j][i] = TYHJA;
            }
        }
    }

    private boolean tarkistaSiirto(int kor, int lev, int korMin, int levMin) {
        if (nappula[kor][lev].vari() == vuoro) {
            List<int[]> mahdSiirrot = sallitutLiikkeet(kor, lev);
            sotilaanKorottaminen(kor, lev, korMin, levMin, mahdSiirrot);
            for (int[] s : mahdSiirrot) {
                if (s[0] == korMin && s[1] == levMin) {
                    return true;
                }
            }
        }
        return false;
    }

    private void upseerienSiirrot(int kor, int lev, List<int[]> mahdSiirrot) {
        for (Liikesuunta liike : nappula[kor][lev].liikkeet()) {
            int[][] s = liike.suunnat();
            for (int i = 0; i < s[0].length; i++) {
                if (!lisaaSiirtolistalle(kor, lev, kor + s[0][i], lev + s[1][i], mahdSiirrot)) {
                    break;
                }
            }
        }
    }

    private void sotilaanSiirrot(int kor, int lev, List<int[]> mahdSiirrot) {
        for (int i = 0; i < nappula[kor][lev].liikkeet().size(); i++) {
            int[][] s = nappula[kor][lev].liikkeet().get(i).suunnat();
            if (i == 0) {
                lisaaSotilaanSuoratLiikkeetSiirtolistalle(kor, lev, s, mahdSiirrot);
            } else {
                lisaaSotilaanVinoLiikeSiirtolistalle(kor, lev, kor + s[0][0], lev + s[1][0], mahdSiirrot);
            }
        }
    }
    
    private boolean lisaaSiirtolistalle(int kor, int lev,int korMin, int levMin, List<int[]> mahdSiirrot) {
        if (laudalla(korMin) && laudalla(levMin)) {
            if (nappula[kor][lev].vari() == nappula[korMin][levMin].vari()) {
                return false;
            }
            mahdSiirrot.add(new int[]{korMin, levMin});
            if (!tyhjaRuutu(korMin, levMin)) {
                return false;
            }
        }
        return true;
    }
    
    private void lisaaSotilaanSuoratLiikkeetSiirtolistalle(int kor, int lev,int[][] s, List<int[]> mahdSiirrot) {
        if (tyhjaRuutu(kor + s[0][0],lev + s[1][0])) {
            mahdSiirrot.add(new int[]{kor + s[0][0], lev + s[1][0]});
            if (nappula[kor][lev].siirtojenMaara() == 0 && tyhjaRuutu(kor+s[0][1],lev+s[1][1])) {
                mahdSiirrot.add(new int[]{kor + s[0][1], lev + s[1][1]});
            }
        }
    }
    
    private void lisaaSotilaanVinoLiikeSiirtolistalle(int kor, int lev, int korMin, int levMin, List<int[]> mahdSiirrot) {
        if (laudalla(korMin) && laudalla(levMin) && nappula[kor][lev].vastustaja(nappula[korMin][levMin])) {
            mahdSiirrot.add(new int[]{korMin, levMin});
        }
    }
    
    private boolean laudalla(int paikka) {
        return paikka >= 0 && paikka <= 7;
    }
  
    private void sotilaanKorottaminen(int kor, int lev, int korMin, int levMin, List<int[]> mahdSiirrot) {
        if (nappula[kor][lev].nimi() == 's') {
            for (int[] s : mahdSiirrot) {
                if (s[0] == korMin && s[1] == levMin && (korMin == 0 || korMin == 7)) {
                    PELILAUTA[kor][lev] = new Kuningatar(nappula[kor][lev].vari(), kor, lev);
                    paivita = true;
                }
            }
        }
    }

    private void syodaankoKuningas(int kor, int lev) {
        if (nappula[kor][lev].nimi() == 'k') {
            peliPaattyi = true;
        }
    }

    private void vaihdaPaikat(int kor, int lev, int korMin, int levMin) {
        nappula[kor][lev].uusiSijainti(korMin, levMin);
        PELILAUTA[korMin][levMin] = nappula[kor][lev];
        PELILAUTA[kor][lev] = TYHJA;  
    }
}
