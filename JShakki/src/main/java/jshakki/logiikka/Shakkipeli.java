
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
    
    public boolean siirto(int korMista, int levMista, int korMinne, int levMinne) {
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
        return ruutu(kir(mj,0), num(mj,1));
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
        PELILAUTA[0][0] = new Torni(Vari.VALKOINEN);
        PELILAUTA[0][1] = new Ratsu(Vari.VALKOINEN);
        PELILAUTA[0][2] = new Lähetti(Vari.VALKOINEN);
        PELILAUTA[0][3] = new Kuningatar(Vari.VALKOINEN);
        PELILAUTA[0][4] = new Kuningas(Vari.VALKOINEN);
        PELILAUTA[0][5] = new Lähetti(Vari.VALKOINEN);
        PELILAUTA[0][6] = new Ratsu(Vari.VALKOINEN);
        PELILAUTA[0][7] = new Torni(Vari.VALKOINEN);
        PELILAUTA[7][0] = new Torni(Vari.MUSTA);
        PELILAUTA[7][1] = new Ratsu(Vari.MUSTA);
        PELILAUTA[7][2] = new Lähetti(Vari.MUSTA);
        PELILAUTA[7][3] = new Kuningatar(Vari.MUSTA);
        PELILAUTA[7][4] = new Kuningas(Vari.MUSTA);
        PELILAUTA[7][5] = new Lähetti(Vari.MUSTA);
        PELILAUTA[7][6] = new Ratsu(Vari.MUSTA);
        PELILAUTA[7][7] = new Torni(Vari.MUSTA);
        sotilaat();
        tyhjat();
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
