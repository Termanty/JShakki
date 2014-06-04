
package jshakki.logiikka;

/**
 * Vari enum osaa shakkinappuloiden värit.
 */
public enum Vari {
    
    VALKOINEN("valkoinen"), MUSTA("musta");
    
    private final String NIMI;
    
    Vari(String nimi) {
        this.NIMI = nimi;
    }

    @Override
    public String toString() {
        return NIMI;
    } 
}
