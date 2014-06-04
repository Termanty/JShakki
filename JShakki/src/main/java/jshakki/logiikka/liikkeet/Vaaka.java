
package jshakki.logiikka.liikkeet;

/**
 * Luokkassa on vaaka- ja pystysuuntaiset liikkeet.
 * Näitä liikkeitä käyttävät torni ja kuningatar.
 */
public enum Vaaka implements Liikesuunta {
    YLOS(1,0), 
    ALAS(-1,0), 
    OIKEA(0,1), 
    VASEN(0,-1);
    
    private final int[][] LIIKKEET;
    private final int MÄÄRÄ = 7;
        
    Vaaka(int pystyLiike, int vaakaLiike) {
        LIIKKEET = new int[2][MÄÄRÄ];
        for (int i = 0; i < MÄÄRÄ; i++) {
            LIIKKEET[0][i] = pystyLiike * (i+1);
            LIIKKEET[1][i] = vaakaLiike * (i+1);
        }
    }

    @Override
    public int[][] suunnat() {
        return LIIKKEET;
    }
}
