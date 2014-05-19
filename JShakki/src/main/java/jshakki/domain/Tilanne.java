
package jshakki.domain;

import java.util.ArrayList;

/**
 *
 * @author termanty
 */
public class Tilanne {
    private ArrayList<Nappula> nappulat;
    private Nappula[][] tilanne;
    
    public Tilanne() {
        nappulat = new ArrayList();
        
    }
    
    public void lisaa(Nappula uusi) {
        nappulat.add(uusi);
    }
    
}
