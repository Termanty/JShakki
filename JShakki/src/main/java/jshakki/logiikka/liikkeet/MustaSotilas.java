

package jshakki.logiikka.liikkeet;

/**
 * Luokkassa on mustan sotilaan liikkeet.
 */
public enum MustaSotilas implements Liikesuunta { 
    ALAS(-1,0,2),  
    ALASOIKEA(-1,1,1), 
    ALASVASEN(-1,-1,1);
    
    private final int[][] LIIKKEET;
        
    MustaSotilas(int pystyLiike, int vaakaLiike, int maara) {
        LIIKKEET = new int[2][maara];
        for (int i = 0; i < maara; i++) {
            LIIKKEET[0][i] = pystyLiike * (i+1);
            LIIKKEET[1][i] = vaakaLiike * (i+1);
        }
    }

    @Override
    public int[][] suunnat() {
        return LIIKKEET;
    } 
}
