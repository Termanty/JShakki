
package jshakki.logiikka;

import jshakki.logiikka.nappulat.*;


/**
 *
 * @author termanty
 */
public class ShakkiPeli {
    private final Ruutu[][] PELILAUTA;
    private final Tyhja TYHJA = new Tyhja();
    private Vari vuoro;
    private int vuoroNro;
    private boolean peliPaattyi;
    
    public final int LEV = 8;
    public final int KOR = 8;

    public ShakkiPeli() {
        this.PELILAUTA = new Ruutu[KOR][LEV];
        pelitilanteenAlustus();
        this.vuoro = Vari.VALKOINEN;
        this.vuoroNro = 1;
        this.peliPaattyi = false;
    }

    public void aloitaPeli() {
        
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
        int levMista = (int) (mj.charAt(0) - 'a');
        int korMista = (int) (mj.charAt(1) - '1');
        int levMinne = (int) (mj.charAt(2) - 'a');
        int korMinne = (int) (mj.charAt(3) - '1');
        return siirto(korMista, levMista, korMinne, levMinne);
    }  

    public Ruutu ruutu(int kor, int lev) {
        return PELILAUTA[kor][lev];
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
        for (int i = 0; i < LEV; i++) {
            PELILAUTA[1][i] = new Sotilas(Vari.VALKOINEN);
            PELILAUTA[6][i] = new Sotilas(Vari.MUSTA);
        }
        for (int i = 0; i < LEV; i++) {
            for (int j = 2; j < 6; j++) {
                PELILAUTA[j][i] = TYHJA;
            }
        }
    }

    
}
