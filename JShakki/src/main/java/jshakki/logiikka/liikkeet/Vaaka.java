
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
    
    private int[][] liikkeet;
        
    Vaaka(int pystyLiike, int vaakaLiike) {
        for (int i = 0; i < 7; i++) {
            liikkeet[0][i] = pystyLiike * (i+1);
            liikkeet[1][i] = vaakaLiike * (i+1);
        }
    }

    @Override
    public int[][] siirrot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
