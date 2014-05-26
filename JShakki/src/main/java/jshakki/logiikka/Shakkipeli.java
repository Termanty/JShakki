
package jshakki.logiikka;

import jshakki.logiikka.nappulat.*;

/**
 *
 * @author termanty
 */
public class Shakkipeli {
    private final Ruutu[][] PELILAUTA;
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
    
    public int[][] mahdollisetSiirrot(int kor, int lev) {
        return null;
    }
    
    public boolean siirto(int levMista, int korMista, int levMinne, int korMinne) {
        PELILAUTA[korMinne][levMinne] = PELILAUTA[korMista][levMista];
        PELILAUTA[korMista][levMista] = TYHJA;
        vaihdaVuoro();
        return true;
    }
    
    public boolean siirto(String mj) {
        return siirto(kir(mj,0), num(mj,1), kir(mj,2), num(mj,3));
    }  

    public Ruutu ruutu(int kor, int lev) {
        return PELILAUTA[kor][lev];
    }
    
    public Ruutu ruutu(String mj) {
        return ruutu(num(mj,1),kir(mj,0));
    }
    
    private int kir(String mj, int i) {
        return (int) (mj.charAt(i) - 'a');
    }
    
    private int num(String mj, int i) {
        return (int) (mj.charAt(i) - '1');
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
    
    private void pelitilanteenAlustus() {
        korkeaArvoiset(Vari.VALKOINEN, 0);
        korkeaArvoiset(Vari.MUSTA, 7);
        sotilaat();
        tyhjat();
    }
    
    private void korkeaArvoiset(Vari vari, int rivi) {
        PELILAUTA[rivi][3] = new Kuningatar(vari);
        PELILAUTA[rivi][4] = new Kuningas(vari);
        aliupseerit(vari, rivi, 0);
        aliupseerit(vari, rivi, 5);
    }
    
    private void aliupseerit(Vari vari, int rivi, int alku) {
        PELILAUTA[rivi][alku] = new Torni(vari);
        PELILAUTA[rivi][alku+1] = new Ratsu(vari);
        PELILAUTA[rivi][alku+2] = new Lähetti(vari);
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

    
}
