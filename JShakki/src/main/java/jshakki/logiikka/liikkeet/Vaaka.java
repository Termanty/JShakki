
package jshakki.logiikka.liikkeet;

/**
 *
 * @author termanty
 */
public enum Vaaka implements Liike {
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
    public int[][] siirrot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}