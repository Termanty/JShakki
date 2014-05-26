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
public enum Hyppy implements Liike {
    YLOSOIKEA(2,1),
    YLOSVASEN(2,-1),
    ALASOIKEA(-2,1),
    ALASVASEN(-2,-1),
    OIKEAYLOS(1,2),
    OIKEAALAS(-1,2),
    VASENYLOS(-1,2),
    VASENALAS(-1,-2);
    
    private final int[][] LIIKKEET;
        
    Hyppy(int pystyLiike, int vaakaLiike) {
        LIIKKEET = new int[2][1];
        LIIKKEET[0][0] = pystyLiike;
        LIIKKEET[1][0] = vaakaLiike; 
    }

    @Override
    public int[][] siirrot() {
        return LIIKKEET;
    }
    
}
