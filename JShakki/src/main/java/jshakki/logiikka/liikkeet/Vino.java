
package jshakki.logiikka.liikkeet;

/**
 * Luokkassa on vinot liikkeet.
 * Näitä liikkeitä käyttävät lähetti ja kuningatar.
 */
public enum Vino implements Liikesuunta {
    YLOSOIKEA(1,1), 
    YLOSVASEN(1,-1), 
    ALASOIKEA(-1,1), 
    ALASVASEN(-1,-1);
    
    private final int[][] LIIKKEET;
    private final int MÄÄRÄ = 7;
        
    Vino(int pystyLiike, int vaakaLiike) {
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
