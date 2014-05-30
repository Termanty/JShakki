
package jshakki.logiikka;

import java.util.ArrayList;
import java.util.List;
import jshakki.logiikka.liikkeet.Liikesuunta;
import jshakki.logiikka.nappulat.*;

/**
 *
 * @author termanty
 */
public class Shakkipeli {
    private final Ruutu[][] PELILAUTA;
    private Ruutu[][] nappula;
    private final Tyhja TYHJA = new Tyhja();
    private Vari vuoro;
    private int vuoroNro;
    private boolean peliPaattyi;
    
    public final int LEV = 8;
    public final int KOR = 8;

    public Shakkipeli() {
        this.PELILAUTA = new Ruutu[KOR][LEV];
        this.nappula = this.PELILAUTA;
        pelitilanteenAlustus();
        this.vuoro = Vari.VALKOINEN;
        this.vuoroNro = 1;
        this.peliPaattyi = false;
    }
    
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
    
    public boolean siirto(int kor, int lev, int korMinne, int levMinne) {
        if (tarkistaSiirto(kor, lev, korMinne, levMinne)) {
            syodaankoKuningas(korMinne, levMinne);
            nappula[kor][lev].kasvataSiirtoLaskuria();
            PELILAUTA[korMinne][levMinne] = nappula[kor][lev];
            PELILAUTA[kor][lev] = TYHJA;        
            vaihdaVuoro();
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
        upseerit(vari, rivi, 0, 1);
        upseerit(vari, rivi, 7, -1);
    }
    
    private void upseerit(Vari vari, int rivi, int alku, int suunta) {
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
        if (nappula[kor][lev].vari() == vuoro) {
            List<int[]> mahdSiirrot = sallitutLiikkeet(kor, lev);
            sotilaanKorottaminen(kor, lev, korMinne, levMinne, mahdSiirrot);
            for (int[] s : mahdSiirrot) {
                if (s[0] == korMinne && s[1] == levMinne) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<int[]> sallitutLiikkeet(int kor, int lev) {
        List<int[]> mahdSiirrot = new ArrayList<>();
        if (nappula[kor][lev].nimi() == 's') {
            sotilaanSiirrot(kor, lev, mahdSiirrot);
        } else {
            upseerienSiirrot(kor, lev, mahdSiirrot);
        }
        return mahdSiirrot;
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
    
    private boolean tyhjaRuutu(int kor, int lev) {
        return PELILAUTA[kor][lev] == TYHJA;
    }
  
    private void sotilaanKorottaminen(int kor, int lev, int korMinne, int levMinne, List<int[]> mahdSiirrot) {
        if (nappula[kor][lev].nimi() == 's') {
            for (int[] s : mahdSiirrot) {
                if (s[0] == korMinne && s[1] == levMinne && (korMinne == 0 || korMinne == 7)) {
                    PELILAUTA[kor][lev] = new Kuningatar(nappula[kor][lev].vari());
                }
            }
        }
    }

    private void syodaankoKuningas(int kor, int lev) {
        if (nappula[kor][lev].nimi() == 'k') {
            peliPaattyi = true;
        }
    }

    

    

   
   
    
}
