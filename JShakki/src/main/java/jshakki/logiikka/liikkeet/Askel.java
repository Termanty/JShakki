
package jshakki.logiikka.liikkeet;

/**
 * Askel enum sisältää Kuninkaan liikkeet.
 */
public enum Askel implements Liikesuunta {
    YLOS(1,0), 
    ALAS(-1,0), 
    OIKEA(0,1), 
    VASEN(0,-1), 
    YLOSOIKEA(1,1), 
    YLOSVASEN(1,-1), 
    ALASOIKEA(-1,1), 
    ALASVASEN(-1,-1);
    
    private final int[][] LIIKKEET;
        
    Askel(int pystyLiike, int vaakaLiike) {
        LIIKKEET = new int[2][1];
        LIIKKEET[0][0] = pystyLiike;
        LIIKKEET[1][0] = vaakaLiike; 
    }

    @Override
    public int[][] suunnat() {
        return LIIKKEET;
    }
}
