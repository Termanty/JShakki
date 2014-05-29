
package jshakki.logiikka;

import java.util.ArrayList;
import jshakki.logiikka.liikkeet.Liike;
import jshakki.logiikka.nappulat.*;

/**
 *
 * @author termanty
 */
public class Shakkipeli {
    final Ruutu[][] PELILAUTA;
    private final Tyhja TYHJA = new Tyhja();
    private Vari vuoro;
    private int vuoroNro;
    private boolean peliPaattyi;
    
    public final int LEV = 8;
    public final int KOR = 8;

    public Shakkipeli() {
        this.PELILAUTA = new Ruutu[KOR][LEV];
        pelitilanteenAlustus();
        this.vuoro = Vari.VALKOINEN;
        this.vuoroNro = 1;
        this.peliPaattyi = false;
    }
    
    public void vaihdaVuoro() {
        if (this.vuoro == Vari.VALKOINEN) {
            this.vuoro = Vari.MUSTA;
        } else {
            this.vuoro = Vari.VALKOINEN;
            this.vuoroNro++;
        }
    }
    
    public boolean siirto(int kor, int lev, int korMinne, int levMinne) {
        if (tarkistaSiirto(kor, lev, korMinne, levMinne)) {
            ruutu(kor,lev).kasvataSiirtoLaskuria();
            if (!syotiinkoKuningas(korMinne, levMinne)) {
                vaihdaVuoro();
            }
            PELILAUTA[korMinne][levMinne] = PELILAUTA[kor][lev];
            PELILAUTA[kor][lev] = TYHJA;
            return true;
        }
        return false;
    }
    
    public boolean siirto(String mj) {
        return siirto(num(mj,1), kir(mj,0), num(mj,3), kir(mj,2));
    }  

    public Ruutu ruutu(int kor, int lev) {
        return PELILAUTA[kor][lev];
    }
    
    public Ruutu ruutu(String mj) {
        return ruutu(num(mj,1),kir(mj,0));
    }
    
    public boolean loppu() {
        return peliPaattyi;
    }
    
    public String vuoro() {
        return this.vuoro.name();
    }
    
    public int vuoroNro() {
        return this.vuoroNro;
    }
    
 
    
    
    /// PRIVATE METODIT
    
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
        PELILAUTA[rivi][3] = new Kuningatar(vari);
        PELILAUTA[rivi][4] = new Kuningas(vari);
        aliupseerit(vari, rivi, 0, 1);
        aliupseerit(vari, rivi, 7, -1);
    }
    
    private void aliupseerit(Vari vari, int rivi, int alku, int suunta) {
        PELILAUTA[rivi][alku] = new Torni(vari);
        PELILAUTA[rivi][alku+(1*suunta)] = new Ratsu(vari);
        PELILAUTA[rivi][alku+(2*suunta)] = new Lahetti(vari);
    }
    
    private void sotilaat() {
        for (int i = 0; i < LEV; i++) {
            PELILAUTA[1][i] = new Sotilas(Vari.VALKOINEN);
            PELILAUTA[6][i] = new Sotilas(Vari.MUSTA);
        }
    }
    
    private void tyhjat() {
        for (int i = 0; i < LEV; i++) {
            for (int j = 2; j < 6; j++) {
                PELILAUTA[j][i] = TYHJA;
            }
        }
    }

    private boolean tarkistaSiirto(int kor, int lev, int korMinne, int levMinne) {
        if (!(this.vuoro == ruutu(kor, lev).vari())) {
            return false;
        }
        ArrayList<int[]> mahdSiirrot = sallitutLiikkeet(kor, lev);
        sotilaanKorottaminen(kor, lev, korMinne, levMinne, mahdSiirrot);
        for (int[] s : mahdSiirrot) {
            if (s[0] == korMinne && s[1] == levMinne) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<int[]> sallitutLiikkeet(int kor, int lev) {
        ArrayList<int[]> mahdSiirrot = new ArrayList<>();
        Ruutu siirettava = ruutu(kor,lev);
        if (siirettava.nimi() == 's') {
            sotilaanSiirrot(kor, lev, mahdSiirrot);
        } else {
            upseerienSiirrot(kor, lev, mahdSiirrot);
        }
        return mahdSiirrot;
    }

    private void upseerienSiirrot(int kor, int lev, ArrayList<int[]> mahdSiirrot) {
        for (Liike l : ruutu(kor,lev).liikkeet()) {
            int[][] s = l.siirrot();
            for (int i = 0; i < s[0].length; i++) {
                if (!(laudalla(kor+s[0][i]) && laudalla(lev+s[1][i]))) {
                    break;
                }
                Ruutu minne = ruutu(kor+s[0][i], lev+s[1][i]);
                if (ruutu(kor,lev).vari() == minne.vari()) {
                    break;
                }
                mahdSiirrot.add(new int[]{kor + s[0][i], lev + s[1][i]});
                if (minne != TYHJA) {
                    break;
                }
            }
        }
    }

    private void sotilaanSiirrot(int kor, int lev, ArrayList<int[]> mahdSiirrot) {
        int[][] s = ruutu(kor,lev).liikkeet().get(0).siirrot();
        if (tyhja(kor+s[0][0],lev+s[1][0])) {
            mahdSiirrot.add(new int[]{kor + s[0][0], lev + s[1][0]});
            if (ruutu(kor,lev).siirtojenMaara() == 0 && tyhja(kor+s[0][1],lev+s[1][1])) {
                mahdSiirrot.add(new int[]{kor + s[0][1], lev + s[1][1]});
            }
        }
        for (int i = 1; i < 3; i++) {
            s = ruutu(kor,lev).liikkeet().get(i).siirrot();
            if (laudalla(kor+s[0][0]) && laudalla(lev+s[1][0]) && ruutu(kor,lev).vastustaja(ruutu(kor+s[0][0], lev+s[1][0]))) {
                mahdSiirrot.add(new int[]{kor+s[0][0], lev+s[1][0]});
            }
        }
    }
    
    private boolean laudalla(int paikka) {
        return paikka >= 0 && paikka <= 7;
    }
    
    private boolean tyhja(int kor, int lev) {
        return ruutu(kor,lev) == TYHJA;
    }
  
    private void sotilaanKorottaminen(int kor, int lev, int korMinne, int levMinne, ArrayList<int[]> mahdSiirrot) {
        if (ruutu(kor, lev).nimi() == 's') {
            for (int[] s : mahdSiirrot) {
                if (s[0] == korMinne && s[1] == levMinne && (korMinne == 0 || korMinne == 7)) {
                    PELILAUTA[kor][lev] = new Kuningatar(ruutu(kor,lev).vari());
                }
            }
        }
    }

    private boolean syotiinkoKuningas(int kor, int lev) {
        if (ruutu(kor,lev).nimi() == 'k') {
            peliPaattyi = true;
        }
        return peliPaattyi;
    }
   
    
}
