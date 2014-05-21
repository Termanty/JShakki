/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jshakki.logiikka.liikkeet;

/**
 *
 * @author termanty
 */
public enum ValkeaSotilas implements Liike {
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
    public int[][] siirrot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
