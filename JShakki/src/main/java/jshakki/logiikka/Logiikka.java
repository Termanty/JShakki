
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

    /**
     * Pelilaudan ruutujen määrä leveyssuunnassa.
     */
    public final int LEV = 8;

    /** 
     * Pelilaudan ruujen määrä korkeussuunnassa.
     */
    public final int KOR = 8;

    /**
     * Lista syödyistä nappuloista.
     */
    public List<Ruutu> syodyt;
    
    /**
     * Pelilauta 8*8 ruudukkonna.
     */
    private final Ruutu[][] PELILAUTA;
    
    /**
     * Pelilauta 8*8 ruudukkonna.
     */
    private final Ruutu[][] nappula; // sama kuin PELILAUTA. Tarkoituksena selkeyttää koodia jäljempänä.
    
    /**
     * Pelilaudan tyhja ruutu.
     */
    private final Tyhja TYHJA;
    
    /**
     * Tähän luokkaan tallennetaan tehdyt siirrot.
     */
    private final Pelihistoria historia;
    
    /**
     * Tämä muuttuja kertoo onko pelivuoro valkoisella vai mustalla pelaajalla.
     */
    private Vari vuoro;
    
    /**
     * Tämä muuttuja tietää kuinka mones vuoro on menossa.
     */
    private int vuoroNro;
    
    /**
     * Tämän muutujan arvo on true, kun kuningas on syöty.
     */
    private boolean peliPaattyi;
    
    /**
     * Tämä muutuja kertoo graafiselle käyttöliitymälle että nappuloiden tiedot
     * pitäisi päivittää. 
     */
    private boolean paivita;
   
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
     * @param korMista on nappulan nykyinen ruutu korkeussuunnassa.
     * @param levMista on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMinne on uusi ruutu korkeussuunnassa.
     * @param levMinne on uusi ruutu leveyssuunnassa.
     * @return palautaa true jos siirto hyväksyttiin, muulloin false.
     */
    public boolean siirto(int korMista, int levMista, int korMinne, int levMinne) {
        Ruutu syotava = nappula[korMinne][levMinne];
        if (!peliPaattyi && tarkistaSiirto(korMista, levMista, korMinne, levMinne)) {
            teeSiirto(korMista, levMista, korMinne, levMinne, syotava);
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
    
    
    // gettereitä
    
    public boolean loppu() {
        return this.peliPaattyi;
    }
    
    public String vuoro() {
        return this.vuoro.name();
    }
    
    public int vuoroNro() {
        return this.vuoroNro;
    }
    
    
/// PRIVATE METODIT ******************************************************************
    
    /**
     * Tässä suoritetaan shakkinappulan siirto.
     * @param korMista on nappulan nykyinen ruutu korkeussuunnassa.
     * @param levMista on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMinne on uusi ruutu korkeussuunnassa.
     * @param levMinne on uusi ruutu leveyssuunnassa.
     */
    private void teeSiirto(int korMista, int levMista, int korMinne, int levMinne, Ruutu syotava) {
        liisaaSyoty(syotava);
        syodaankoKuningas(korMinne, levMinne);
        nappula[korMista][levMista].kasvataSiirtoLaskuria();
        vaihdaPaikat(korMista, levMista, korMinne, levMinne);
        historia.tallennaSiirto(korMista, levMista, korMinne, levMinne);
        vaihdaVuoro();
    }
    
    /**
     * Lisaa syodyn nappulan syodyt listalle.
     * @param syotava on ruutu, jossa saattaa olla syotava nappula.
     */
    private void liisaaSyoty(Ruutu syotava) {
        if (syotava != TYHJA) {
            syodyt.add(syotava);
            paivita = true;
        }
    }
    
    /**
     * Muutetaan merkkijonossa oleva kirjain leveyssuuntaiseksi sijainniksi.
     * @param mj on siirron sisältävä merkkijono.
     * @param i on kirjaimen paikka tieto merkkijonossa.
     */
    private int kir(String mj, int i) {
        return (int) (mj.charAt(i) - 'a');
    }
    
    /**
     * Muutetaan merkkijonossa oleva numero korkeussuuntaiseksi sijainniksi.
     * @param mj on siirron sisältävä merkkijono.
     * @param i on numeron paikka tieto merkkijonossa.
     */
    private int num(String mj, int i) {
        return (int) (mj.charAt(i) - '1');
    }
    
    /**
     * Alustetetaan pelilaudan ruudut.
     */
    private void pelitilanteenAlustus() {
        korkeaArvoiset(Vari.VALKOINEN, 0);
        korkeaArvoiset(Vari.MUSTA, 7);
        sotilaat();
        tyhjat();
    }
    
     /**
     * Lisätään kunikaalliset pelilaudalle.
     */
    private void korkeaArvoiset(Vari vari, int rivi) {
        PELILAUTA[rivi][3] = new Kuningatar(vari, rivi, 3);
        PELILAUTA[rivi][4] = new Kuningas(vari, rivi, 4);
        upseerit(vari, rivi, 0, 1);
        upseerit(vari, rivi, 7, -1);
    }
    
    /**
     * Lisätään upseerit pelilaudalle.
     */
    private void upseerit(Vari vari, int rivi, int alku, int suunta) {
        PELILAUTA[rivi][alku] = new Torni(vari, rivi, alku);
        PELILAUTA[rivi][alku+(1*suunta)] = new Ratsu(vari, rivi, alku+(1*suunta));
        PELILAUTA[rivi][alku+(2*suunta)] = new Lahetti(vari, rivi, alku+(2*suunta));
    }
    
    /**
     * Lisätään sotilaat pelilaudalle.
     */
    private void sotilaat() {
        for (int i = 0; i < LEV; i++) {
            PELILAUTA[1][i] = new Sotilas(Vari.VALKOINEN, 1, i);
            PELILAUTA[6][i] = new Sotilas(Vari.MUSTA, 6, i);
        }
    }
    
    /**
     * Lisätään tyhjät ruudut.
     */
    private void tyhjat() {
        for (int i = 0; i < LEV; i++) {
            for (int j = 2; j < 6; j++) {
                PELILAUTA[j][i] = TYHJA;
            }
        }
    }

    /**
     * Tässä tarkistetaan siirron laillisuus.
     * @param korMista on nappulan nykyinen ruutu korkeussuunnassa.
     * @param levMista on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMinne on uusi ruutu korkeussuunnassa.
     * @param levMinne on uusi ruutu leveyssuunnassa.
     * @return palautaa true jos siirto hyväksyttiin, muulloin false.
     */
    private boolean tarkistaSiirto(int korMista, int levMista, int korMinne, int levMinne) {
        if (nappula[korMista][levMista].vari() == vuoro) {
            List<int[]> mahdSiirrot = sallitutLiikkeet(korMista, levMista);
            sotilaanKorottaminen(korMista, levMista, korMinne, levMinne, mahdSiirrot);
            for (int[] s : mahdSiirrot) {
                if (s[0] == korMinne && s[1] == levMinne) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Tässä etsitään upseerien mahdolliset siirrot.
     * @param kor on nappulan nykyinen ruutu korkeussuunnassa.
     * @param lev on nappulan nykyinen ruutu leveyssuunnassa.
     * @param mahdollisetSiirrot on lista siirroista joita nappula voi tehdä.
     */
    private void upseerienSiirrot(int kor, int lev, List<int[]> mahdollisetSiirrot) {
        for (Liikesuunta liike : nappula[kor][lev].liikkeet()) {
            int[][] s = liike.suunnat();
            for (int i = 0; i < s[0].length; i++) {
                if (!lisaaSiirtolistalle(kor, lev, kor + s[0][i], lev + s[1][i], mahdollisetSiirrot)) {
                    break;
                }
            }
        }
    }

    /**
     * Tässä etsitään sotilaan mahdolliset siirrot.
     * @param kor on nappulan nykyinen ruutu korkeussuunnassa.
     * @param lev on nappulan nykyinen ruutu leveyssuunnassa.
     * @param mahdollisetSiirrot on lista siirroista joita nappula voi tehdä.
     */
    private void sotilaanSiirrot(int kor, int lev, List<int[]> mahdollisetSiirrot) {
        for (int i = 0; i < nappula[kor][lev].liikkeet().size(); i++) {
            int[][] siirto = nappula[kor][lev].liikkeet().get(i).suunnat();
            if (i == 0) {
                lisaaSotilaanSuoratLiikkeetSiirtolistalle(kor, lev, siirto, mahdollisetSiirrot);
            } else {
                lisaaSotilaanVinoLiikeSiirtolistalle(kor, lev, kor + siirto[0][0], lev + siirto[1][0], mahdollisetSiirrot);
            }
        }
    }
    
    /**
     * Tässä lisätään siirto mahdollisten siirtojen listalle.
     * @param korMista on nappulan nykyinen ruutu korkeussuunnassa.
     * @param levMista on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMinne on uusi ruutu korkeussuunnassa.
     * @param levMinne on uusi ruutu leveyssuunnassa.
     * @param mahdollisetSiirrot on lista siirroista joita nappula voi tehdä.
     * @return palautaa true jos siirto lisättiin, muulloin false.
     */
    private boolean lisaaSiirtolistalle(int korMista, int levMista,int korMinne, int levMinne, List<int[]> mahdSiirrot) {
        if (laudalla(korMinne) && laudalla(levMinne)) {
            if (nappula[korMista][levMista].vari() == nappula[korMinne][levMinne].vari()) {
                return false;
            }
            mahdSiirrot.add(new int[]{korMinne, levMinne});
            if (!tyhjaRuutu(korMinne, levMinne)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Tässä lisätään sotilaan mahdollinen liike suoraan eteen listalle.
     * @param kor on nappulan nykyinen ruutu korkeussuunnassa.
     * @param lev on nappulan nykyinen ruutu leveyssuunnassa.
     * @param siirto on nappulan muutos nykyiseen sijaintiin nähden.
     * @param mahdollisetSiirrot on lista siirroista joita nappula voi tehdä.
     */
    private void lisaaSotilaanSuoratLiikkeetSiirtolistalle(int kor, int lev,int[][] siirto, List<int[]> mahdollisetSiirrot) {
        if (tyhjaRuutu(kor + siirto[0][0],lev + siirto[1][0])) {
            mahdollisetSiirrot.add(new int[]{kor + siirto[0][0], lev + siirto[1][0]});
            if (nappula[kor][lev].siirtojenMaara() == 0 && tyhjaRuutu(kor+siirto[0][1],lev+siirto[1][1])) {
                mahdollisetSiirrot.add(new int[]{kor + siirto[0][1], lev + siirto[1][1]});
            }
        }
    }
    
    /**
     * Tässä lisätään sotilaan mahdollinen vino syönti listalle.
     * @param korMista on nappulan nykyinen ruutu korkeussuunnassa.
     * @param levMista on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMinne on uusi ruutu korkeussuunnassa.
     * @param levMinne on uusi ruutu leveyssuunnassa.
     * @param mahdollisetSiirrot on lista siirroista joita nappula voi tehdä.
     */
    private void lisaaSotilaanVinoLiikeSiirtolistalle(int korMista, int levMista, int korMinne, int levMinne, List<int[]> mahdollisetSiirrot) {
        if (laudalla(korMinne) && laudalla(levMinne) && nappula[korMista][levMista].vastustaja(nappula[korMinne][levMinne])) {
            mahdollisetSiirrot.add(new int[]{korMinne, levMinne});
        }
    }
    
    /**
     * Tässä sotilas korotetaan kuningattareksi.
     * @param paikka on sijainti indeksi.
     * @return true jos pelilaudalla.
     */
    private boolean laudalla(int paikka) {
        return paikka >= 0 && paikka <= 7;
    }
  
    /**
     * Tässä sotilas korotetaan kuningattareksi.
     * @param korMista on nappulan nykyinen ruutu korkeussuunnassa.
     * @param levMista on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMinne on uusi ruutu korkeussuunnassa.
     * @param levMinne on uusi ruutu leveyssuunnassa.
     * @param mahdollisetSiirrot on lista siirroista joita nappula voi tehdä.
     */
    private void sotilaanKorottaminen(int korMista, int levMista, int korMinne, int levMinne, List<int[]> mahdollisetSiirrot) {
        if (nappula[korMista][levMista].nimi() == 's') {
            for (int[] s : mahdollisetSiirrot) {
                if (s[0] == korMinne && s[1] == levMinne && (korMinne == 0 || korMinne == 7)) {
                    PELILAUTA[korMista][levMista] = new Kuningatar(nappula[korMista][levMista].vari(), korMista, levMista);
                    paivita = true;
                }
            }
        }
    }

    /**
     * Tässä tarkistetaan tuleeko kuningas syödyksi.
     * @param kor on nappulan ruutu korkeussuunnassa.
     * @param lev on nappulan ruutu leveyssuunnassa.
     */
    private void syodaankoKuningas(int kor, int lev) {
        if (nappula[kor][lev].nimi() == 'k') {
            peliPaattyi = true;
        }
    }

    /**
     * Tässä suoritetaan shakkinappuloiden paikkojen vaihto pelilaudalla.
     * @param korMista on nappulan nykyinen ruutu korkeussuunnassa.
     * @param levMista on nappulan nykyinen ruutu leveyssuunnassa.
     * @param korMinne on uusi ruutu korkeussuunnassa.
     * @param levMinne on uusi ruutu leveyssuunnassa.
     */
    private void vaihdaPaikat(int korMista, int levMista, int korMinne, int levMinne) {
        nappula[korMista][levMista].uusiSijainti(korMinne, levMinne);
        PELILAUTA[korMinne][levMinne] = nappula[korMista][levMista];
        PELILAUTA[korMista][levMista] = TYHJA;  
    }
}
