
package jshakki.logiikka.liikkeet;

/**
 * Luokassa on valkoisen sotilaan liikkeet.
 */
public enum ValkeaSotilas implements Liikesuunta {
    YLOS(1,0,2), 
    YLOSOIKEA(1,1,1), 
    YLOSVASEN(1,-1,1);
    
    private final int[][] LIIKKEET;
        
    ValkeaSotilas(int pystyLiike, int vaakaLiike, int maara) {
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
